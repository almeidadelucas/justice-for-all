import React from 'react';
import {
  TextField,
  Box,
  Button,
  FormControl,
  FormLabel,
  RadioGroup,
  FormControlLabel,
  Radio,
  FormHelperText,
} from '@mui/material';
import { useRouter } from 'next/router';
import { FormEvent } from 'react';
import { Form } from '@/components/atoms/Form';
import { Controller, useForm } from 'react-hook-form';
import { useValidateEmail } from '@/hooks/useValidateEmail';
import { useValidateFirstName } from '@/hooks/useValidateFirstName';
import { useValidateLastName } from '@/hooks/useValidateLastName';
import { useValidateCpf } from '@/hooks/useValidateCpf';
import { useValidateOab } from '@/hooks/useValidateOab';
import { useValidatePassword } from '@/hooks/useValidatePassword';

const UserType = {
  LAWYER: 'LAWYER',
  CLIENT: 'CLIENT',
};

export interface IFormValues {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  type: keyof typeof UserType | '';
  cpf?: string;
  oab?: string;
}

export function SignupForm() {
  const {
    control,
    register,
    handleSubmit,
    formState: { errors },
    watch,
  } = useForm<IFormValues>();
  const { push } = useRouter();

  const { emailInputProps } = useValidateEmail(register);
  const { firstNameInputProps } = useValidateFirstName(register);
  const { lastNameInputProps } = useValidateLastName(register);
  const { cpfInputProps } = useValidateCpf(register);
  const { oabInputProps } = useValidateOab(register);
  const { passwordInputProps } = useValidatePassword(register);

  const onSubmit = (data: IFormValues) => {
    fetch('http://localhost:8080/user', {
      method: 'POST',
      body: JSON.stringify(data),
      headers: {
        'Content-Type': 'application/json',
      },
    })
      .then((res) => {
        if (res.status === 201) {
          push('/');
        } else {
          alert('Erro ao cadastrar usuário');
        }
      })
      .catch((err) => {
        alert('Erro ao cadastrar usuário');
        console.error('Error to register new user: ', err.message);
      });
  };

  const customSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    handleSubmit(onSubmit)(event);
  };

  return (
    <Form onSubmit={customSubmit}>
      <Box
        sx={{
          display: 'flex',
          flexDirection: 'column',
          rowGap: '0.5rem',
        }}
      >
        <Box
          sx={{
            display: 'flex',
            '& > *': {
              flex: 1,
            },
            columnGap: '1rem',
          }}
        >
          <TextField
            label="Primeiro Nome"
            required
            variant="standard"
            error={!!errors.firstName}
            helperText={errors.firstName?.message as string}
            {...firstNameInputProps}
          />
          <TextField
            label="Sobrenome"
            required
            variant="standard"
            error={!!errors.lastName}
            helperText={errors.lastName?.message as string}
            {...lastNameInputProps}
          />
        </Box>
        <TextField
          label="Email"
          required
          variant="standard"
          error={!!errors.email}
          helperText={errors.email?.message as string}
          {...emailInputProps}
        />
        <TextField
          label="Senha"
          required
          variant="standard"
          type="password"
          error={!!errors.password}
          helperText={errors.password?.message as string}
          {...passwordInputProps}
        />
        <Controller
          name="type"
          control={control}
          defaultValue=""
          rules={{ required: 'Selecione uma opção' }}
          render={({ field }) => (
            <FormControl
              sx={{ borderBottom: '1px solid grey' }}
              error={!!errors.type}
              required
            >
              <FormLabel>Tipo de usuário</FormLabel>
              <RadioGroup row {...field}>
                <FormControlLabel
                  value={UserType.LAWYER}
                  control={<Radio />}
                  label="Advogado"
                  name="type"
                />
                <FormControlLabel
                  value={UserType.CLIENT}
                  control={<Radio />}
                  label="Cliente"
                  name="type"
                />
              </RadioGroup>
              {errors.type?.message && (
                <FormHelperText>{errors.type.message as string}</FormHelperText>
              )}
            </FormControl>
          )}
        />
        {watch('type') === UserType.LAWYER && (
          <TextField
            label="OAB"
            required
            variant="standard"
            error={!!errors.oab}
            helperText={errors.oab?.message as string}
            {...oabInputProps}
          />
        )}
        {watch('type') === UserType.CLIENT && (
          <TextField
            label="CPF"
            required
            variant="standard"
            error={!!errors.cpf}
            helperText={errors.cpf?.message as string}
            {...cpfInputProps}
          />
        )}
      </Box>
      <Button type="submit" variant="outlined">
        Cadastrar
      </Button>
    </Form>
  );
}
