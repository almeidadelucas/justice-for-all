import React, { FormEvent, useContext, useEffect } from 'react';
import { useState } from 'react';
import { Modal, TextField, Button, Grid, Select, InputLabel, MenuItem, SelectChangeEvent, Box, IconButton } from '@mui/material';
import axios from 'axios';
import CloseIcon from '@mui/icons-material/Close';
import { BotaoArquivo } from '../atoms/BotaoArquivo';
import { AuthContext } from '@/context/AuthContext';
import { useRouter } from 'next/router';

interface EditCaseCommand {
    caseId: number;
    userId: number;
    title: string;
    category: string;
    description: string;
    alegation: string;
    evidencesPDF: string;
    caseIdentifier: string;
}

interface EditCaseFormProps {
    id: number;
    caseDataOriginal: EditCaseCommand;
  }

export function EditCaseForm({ id, caseDataOriginal } : EditCaseFormProps) {
    const [formPronto, setFormPronto] = useState(false);
    const [uploadConcluido, setUploadConcluido] = useState(false);
    const [respostaEnvio, setRespostaEnvio] = useState(Object);
    const [open, setOpen] = useState(false);
    const { push } = useRouter();

    const { token, loggedUser } = useContext(AuthContext);

    const axiosInstance = axios.create({
        baseURL: process.env.JUSTICE_FOR_ALL_API_URL,
        headers: { Authorization: `Basic ${token}` }
    });

    const [caseData, setCase] = useState<EditCaseCommand>({
        caseId: id,
        userId: caseDataOriginal.userId,
        title: caseDataOriginal.title,
        category: caseDataOriginal.category,
        description: caseDataOriginal.description,
        alegation: caseDataOriginal.alegation,
        evidencesPDF: caseDataOriginal.evidencesPDF,
        caseIdentifier: caseDataOriginal.caseIdentifier,
    });

    useEffect(() => {
        setCase((current) => ({
          ...current,
          alegation: caseDataOriginal.alegation || 'INNOCENT',
        }));
    }, [caseDataOriginal]);

    const [erros, setErrors] = useState<EditCaseCommand>({
        caseId: id,
        userId: 0,
        title: '',
        category: '',
        description: '',
        alegation: '',
        evidencesPDF: '',
        caseIdentifier: '',
    });

    useEffect(() => {
        if(validate()) {
            setFormPronto(true)
        }else {
            setFormPronto(false)
        }
    })

    const validate = () => {
        let isValid: boolean = true;
        const errors: Partial<EditCaseCommand> = {};
    
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
        let body: EditCaseCommand = caseData;
        if(respostaEnvio.filesUploaded != undefined || respostaEnvio.filesUploaded != null) {
            body.evidencesPDF = respostaEnvio.filesUploaded[0].url;
        }
        body.caseId = id;
        body.userId = loggedUser?.userId ?? 0;
        console.log("id passado nas propos: ", body);
        console.log(respostaEnvio);
        ///////
        if(validate()) {
        axiosInstance.put('case', body).then((res) => {
            if (res.status == 202) {
            console.log(res);
            setOpen(false);
            push('/show_cases');
            }
        }
        ).catch(err => {
            console.error(err)
            alert('Erro ao atualizar um caso')
        })
        }else {
        alert('Erro ao atualizar um caso')
        }
    };

    const handleOpen = () => {
        setOpen(true);
    };
    
      const handleClose = () => {
        setOpen(false);
        setCase(caseDataOriginal);
    };

    return (
        <div>
            <Button variant="contained" color="primary" onClick={handleOpen}>
                Editar caso
            </Button>
            <Modal open={open} onClose={handleClose}>
            <Box
                sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    justifyContent: 'center',
                    alignItems: 'center',
                    marginTop: '7%',
                  }}>
                    <Box
                        sx={{
                            backgroundColor: '#ffffff', // Cor de fundo do formulário
                            padding: '2rem',
                            borderRadius: '8px',
                            boxShadow: '0px 0px 10px rgba(0, 0, 0, 0.1)', // Sombra no formulário
                        }}
                    >   
                        <form onSubmit={handleSubmit}>
                            <Grid container spacing={2}>
                                <IconButton
                                    sx={{
                                    position: 'relative',
                                    top: '0.5rem',
                                    right: '0.5rem',
                                    zIndex: 1,
                                    }}
                                    onClick={handleClose}
                                >
                                    <CloseIcon />
                                </IconButton>
                                <Grid item xs={12}>
                                <TextField
                                    label="Título"
                                    name="title"
                                    onChange={handleChange}
                                    value={caseData.title}
                                    error={!!erros.title}
                                    helperText={erros.title}
                                    fullWidth
                                />
                                </Grid>
                                <Grid item xs={12}>
                                <TextField
                                    label="Categoria"
                                    name="category"
                                    onChange={handleChange}
                                    value={caseData.category}
                                    error={!!erros.category}
                                    helperText={erros.category}
                                    fullWidth
                                />
                                </Grid>
                                <Grid item xs={12}>
                                <TextField
                                    label="Descrição"
                                    name="description"
                                    onChange={handleChange}
                                    value={caseData.description}
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
                                    name="alegation"
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
                                    label="Identificador do caso"
                                    name="caseIdentifier"
                                    onChange={handleChange}
                                    value={caseData.caseIdentifier}
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
                                    clinderen="Evidencias"
                                />
                                </Grid>
                                <Grid item xs={12}>
                                <Button type="submit" variant="contained" color="primary" disabled={!formPronto}>
                                    Enviar
                                </Button>
                                </Grid>
                            </Grid>
                        </form>
                    </Box>
                </Box>
            </Modal>
        </div>
    );
}