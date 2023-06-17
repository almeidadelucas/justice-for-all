import React, { useContext } from "react";
import { Box, Button, Modal, Typography } from "@mui/material";
import { ModalBox } from "../atoms/ModalBox";
import axios from "axios";
import { AuthContext } from "@/context/AuthContext";

interface IProposalModal {
  caseId?: number;
  open: boolean;
  onClose: () => void;
}

export default function ProposalModal({ caseId, open, onClose }: IProposalModal) {
  const { token } = useContext(AuthContext);

  const handleProposal = async () => {
    try {
      const res = await axios.post(`${process.env.JUSTICE_FOR_ALL_API_URL}/proposal/case/${caseId}`, {}, {headers: { Authorization: `Basic ${token}` }})
      if (res.status === 201) {
        onClose()
      } else {
        alert('Erro ao enviar proposta')
      }
    } catch (err) {
      console.error('Error to send proposal: ', err)
      alert('Erro ao enviar proposta')
    }
  }

  return (
    <Modal open={open} onClose={onClose}>
      <ModalBox display="flex" flexDirection="column" rowGap="1rem">
        <Typography>Deseja enviar uma proposta para ser a pessoa responsável por esse caso?</Typography>
        <Box display="flex" flexDirection="row" columnGap="1rem">
          <Button variant="outlined" onClick={handleProposal}>Sim</Button>
          <Button variant="contained" onClick={onClose}>Cancelar</Button>
        </Box>
      </ModalBox>
    </Modal>
  )
}