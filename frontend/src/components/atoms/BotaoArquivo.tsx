import React, { useState } from "react";
import { PickerOverlay } from 'filestack-react'
import { Button } from "@mui/material";
import CheckCircleIcon from '@mui/icons-material/CheckCircle';

const API_KEY = "AqWWD4a3yTJ2Ln1d9RX3cz";

interface BotaoArquivoProps {
    uploadDone: Function;
    uploadFailed: Function;
    clinderen: string
}

export function BotaoArquivo({uploadDone, uploadFailed, clinderen} : BotaoArquivoProps) {
    const [open, setOpen] = useState(false);
    const [uploadConcluido, setUploadConcluido] = useState(false);

    const handleOpen = () => {
        setOpen(true);
    }

    const handleUploadFailed = () => {
        uploadFailed();
    }

    const handleUploadDone = (res: any) => {
        setUploadConcluido(true);
        uploadDone(res);
    }

    const handleClose = () => {
        setOpen(false);
    }

    return (
        <div>  
            <label className="MuiFormLabel-root MuiInputLabel-root MuiInputLabel-animated MuiFormLabel-colorPrimary MuiInputLabel-root MuiInputLabel-animated css-9npbnl-MuiFormLabel-root-MuiInputLabel-root" id="alegacao-label">
                {clinderen}
            </label>
            <Button onClick={handleOpen}
                    fullWidth 
                    color="primary"
                    variant="contained"
                    disabled={uploadConcluido}>
                {uploadConcluido ? <>
                    <CheckCircleIcon/>
                    Upload concluido
                </>  : "Fazer Upload"}
            </Button>
            {open && <PickerOverlay
                apikey={API_KEY}
                pickerOptions={{
                    onClose: handleClose,
                    onFileUploadFailed: handleUploadFailed,
                    onUploadDone: handleUploadDone,
                    maxFiles: 1,
                    fromSources: ["local_file_system", "googledrive", "dropbox", "onedrive"],
                }}
            />}
        </div>

    )
}