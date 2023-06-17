import React, { FormEvent } from 'react';
import { useState } from 'react';
import { TextField, Box, Button } from '@mui/material';
import axios from 'axios';
import { Form } from '../atoms/Form';

export function CreateCaseForm() {
  const [userId, setUserId] = useState('');
  const [title, setTitle] = useState('');
  const [category, setCategory] = useState('');
  const [description, setDescription] = useState('');
  const [allegation, setAllegation] = useState('');
  const [evidencesPDF, setEvidencesPDF] = useState('');
  const [evidenceImage, setEvidenceImage] = useState('');
  const [caseIdentifier, setCaseIdentifier] = useState('');
  const [open, setOpen] = useState(false);

  const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const createCaseCommand = {
      userId,
      title,
      category,
      description,
      allegation,
      evidencesPDF,
      evidenceImage,
      caseIdentifier,
      open,
    };

    axios
      .post('http://localhost:8080/case', createCaseCommand)
      .then((response) => {
        // Process the successful response here
        console.log('Case created successfully:', response.data);
      })
      .catch((error) => {
        // Handle the error here
        alert("api error")
        console.error('Error creating case:', error);
      });
  };

  return (
    <Form onSubmit={handleSubmit}>
      <Box
        sx={{
          display: 'flex',
          flexDirection: 'column',
          rowGap: '0.8rem',
          width: '80v'
        }}
      >
        <TextField
          label="Titulo"
          required
          variant="standard"
          value={title}
          onChange={(event) => setTitle(event.target.value)}
        />
        <TextField
          label="Categoria"
          required
          variant="standard"
          value={category}
          onChange={(event) => setCategory(event.target.value)}
        />
        <TextField
          label="Descrição"
          required
          variant="standard"
          value={description}
          onChange={(event) => setDescription(event.target.value)}
        />
        <TextField
          label="Alegação"
          required
          variant="standard"
          value={allegation}
          onChange={(event) => setAllegation(event.target.value)}
        />
        <TextField
          label="Evidencias PDF"
          required
          variant="standard"
          value={evidencesPDF}
          onChange={(event) => setEvidencesPDF(event.target.value)}
        />
        <TextField
          label="Evidencias Image"
          required
          variant="standard"
          value={evidenceImage}
          onChange={(event) => setEvidenceImage(event.target.value)}
        />
        <TextField
          label="Case Identifier"
          required
          variant="standard"
          value={caseIdentifier}
          onChange={(event) => setCaseIdentifier(event.target.value)}
        />
      </Box>
      <Button type="submit" variant="outlined">
        Create Case
      </Button>
    </Form>
  );
}