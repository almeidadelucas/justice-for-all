import React, { FormEvent } from 'react';
import { useState } from 'react';
import { TextField, Button, Grid, Select, InputLabel, MenuItem, SelectChangeEvent } from '@mui/material';
import axios from 'axios';
import { PickerOverlay } from 'filestack-react'

const API_KEY = "AqWWD4a3yTJ2Ln1d9RX3cz";

interface createCaseCommand {
	title: string;
	category: string;
  description: string;
  alegation: string;
  evidencesPDF: string;
  caseIdentifier: string;
}

export function CreateCaseForm() {

  const [caseData, setCase] = useState<createCaseCommand>({
    title: '',
    category: '',
    description: '',
    alegation: '',
    evidencesPDF: '',
    caseIdentifier: '',
  });

  const [erros, setErrors] = useState<createCaseCommand>({
    title: '',
    category: '',
    description: '',
    alegation: '',
    evidencesPDF: '',
    caseIdentifier: '',
  });

  const validate = () => {
    let isValid: boolean = true;
    const errors: Partial<createCaseCommand> = {};
  
    if (caseData.title.length < 5) {
      errors.title = 'O título é obrigatório e deve ter mais de 5 caracteres.';
      isValid = false;
    }
  
    if (caseData.category.length < 5) {
      errors.category = 'A categoria é obrigatória e deve ter mais de 5 caracteres.';
      isValid = false;
    }
  
    if (caseData.description.length < 20) {
      errors.description = 'A descrição é obrigatória e deve ter mais de 20 caracteres.';
      isValid = false;
    }
  
    if (caseData.caseIdentifier === '') {
      errors.caseIdentifier = 'O caso precisa ter um identificador da justiça brasileira, normalmente é encontrado na abertura do processo.';
      isValid = false;
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
  };

  const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    if(validate()) {
      console.log(caseData);
    }else {
      alert("?????/")
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
              value='INNOCENT'
              onChange={handleChange}
              fullWidth
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
            <Button onClick={() => alert('teste')}>Open File</Button>
          </Grid>
          <Grid item xs={12}>
            <Button type="submit" variant="contained" color="primary">
              Enviar
            </Button>
          </Grid>
        </Grid>
      </form>
    );
}