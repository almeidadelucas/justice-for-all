import React, { FormEvent, useContext, useEffect } from 'react';
import { useState } from 'react';
import { TextField, Button, Grid, Select, InputLabel, MenuItem, SelectChangeEvent } from '@mui/material';
import axios from 'axios';
import { BotaoArquivo } from '../atoms/BotaoArquivo';
import { AuthContext } from '@/context/AuthContext';
import { useRouter } from 'next/router';

interface createCaseCommand {
  id: number,
	title: string;
	category: string;
  description: string;
  alegation: string;
  evidencesPDF: string;
  caseIdentifier: string;
  open: boolean
}

export function CreateCaseForm() {
  const [formPronto, setFormPronto] = useState(false);
  const [uploadConcluido, setUploadConcluido] = useState(true); ///////mudar para "false" - agora está em debug
  const [respostaEnvio, setRespostaEnvio] = useState(Object);
  const { push } = useRouter();

  const { token, loggedUser } = useContext(AuthContext);

  const axiosInstance = axios.create({
    baseURL: process.env.JUSTICE_FOR_ALL_API_URL,
    headers: { Authorization: `Basic ${token}` }
  })

  const [caseData, setCase] = useState<createCaseCommand>({
    id: 0,
    title: '',
    category: '',
    description: '',
    alegation: 'INNOCENT',
    evidencesPDF: '',
    caseIdentifier: '',
    open: true
  });

  const [erros, setErrors] = useState<createCaseCommand>({
    id: 0,
    title: '',
    category: '',
    description: '',
    alegation: '',
    evidencesPDF: '',
    caseIdentifier: '',
    open: true
  });

  useEffect(() => {
    setCase((current) => ({
      ...current,
      alegation: 'INNOCENT',
    }));
  }, []);

  const validate = () => {
    let isValid: boolean = true;
    const errors: Partial<createCaseCommand> = {};
  
    if (caseData.title != '' && caseData.title.length < 5) {
      errors.title = 'O título é obrigatório e deve ter mais de 5 caracteres.';
      isValid = false;
    }else {
      errors.title = '';
    }
  
    if (caseData.category != '' && caseData.category.length < 5) {
      errors.category = 'A categoria é obrigatória e deve ter mais de 5 caracteres.';
      isValid = false;
    }else {
      errors.category = '';
    }
  
    if (caseData.description != '' && caseData.description.length < 20) {
      errors.description = 'A descrição é obrigatória e deve ter mais de 20 caracteres.';
      isValid = false;
    }else {
      errors.description = '';
    }
  
    if (caseData.caseIdentifier != '' && caseData.caseIdentifier.length < 2) {
      errors.caseIdentifier = 'O caso precisa ter um identificador da justiça brasileira, normalmente é encontrado na abertura do processo.';
      isValid = false;
    }else {
      errors.caseIdentifier = '';
    }
  
    setErrors((prevErrors) => ({ ...prevErrors, ...errors }));
    return isValid;
  };


  const handleChange = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement> | SelectChangeEvent<string>) => {
    const fieldName =  event.target.name;
    const fieldValue = event.target.value;
    setCase(current => ({
      ...current,
      [fieldName]: fieldValue
    }));
    if(validate()) {
      setFormPronto(true)
    }else {
      setFormPronto(false)
    }
  };

  const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    ///////
    let body: createCaseCommand = caseData;
    body.evidencesPDF = respostaEnvio.filesUploaded[0].url;
    body.id = loggedUser?.userId ?? 0;
    console.log(respostaEnvio);
    ///////
    if(validate()) {
      axiosInstance.post('case', body).then((res) => {
        if (res.status === 201) {
          console.log(res);
          push('/show_cases');
        }
      }
      ).catch(err => {
        console.error(err)
        alert('Erro ao criar um caso')
      })
    }else {
      alert('Erro ao criar um caso')
    }
  };

  return (
      <form onSubmit={handleSubmit}>
        <Grid container spacing={2}>
          <Grid item xs={12}>
            <TextField
              required
              label="Título"
              name = "title"
              onChange={handleChange}
              error={!!erros.title}
				      helperText={erros.title}
              fullWidth
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              required
              label="Categoria"
              name='category'
              onChange={handleChange}
              error={!!erros.category}
				      helperText={erros.category}
              fullWidth
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              required
              label="Descrição"
              name='description'
              onChange={handleChange}
              error={!!erros.description}
				      helperText={erros.description}
              fullWidth
              multiline
              rows={4}
            />
          </Grid>
          <Grid item xs={12}>
            <InputLabel id="alegacao-label">Alegação</InputLabel>
            <Select
              labelId="alegacao-label"
              id="alegacao-select"
              name='alegation'
              onChange={handleChange}
              fullWidth
              value={caseData.alegation}
            >
              <MenuItem value="INNOCENT">Inocente</MenuItem>
              <MenuItem value="GUITY">Culpado</MenuItem>
            </Select>
          </Grid>
          <Grid item xs={12}>
            <TextField
              required
              label="Identificador do caso"
              name='caseIdentifier'
              onChange={handleChange}
              error={!!erros.caseIdentifier}
				      helperText={erros.caseIdentifier}
              fullWidth
              multiline
              rows={1}
            />
          </Grid>
          <Grid item xs={12}>
            <BotaoArquivo
              uploadDone={(res: any) => {
                setUploadConcluido(true);
                setRespostaEnvio(res);
              }}
              uploadFailed={() => {
                setUploadConcluido(false);
                setRespostaEnvio(null);
              }}
              clinderen = {
                "Evidencias"
              }
            ></BotaoArquivo>
          </Grid>
          <Grid item xs={12}>
            <Button type="submit" variant="contained" color="primary" disabled={!uploadConcluido || !formPronto}>
              Enviar
            </Button>
          </Grid>
        </Grid>
      </form>
    );
}