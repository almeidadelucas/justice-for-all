import React, { useContext, useEffect, useState } from "react";
import { Box, Button, CircularProgress, Modal, Typography } from "@mui/material";
import { ModalBox } from "../atoms/ModalBox";
import axios from "axios";
import { AuthContext } from "@/context/AuthContext";
import { IProposal } from "./ReceivedProposalModal";

interface IProposalModal {
  caseId?: number;
  open: boolean;
  onClose: () => void;
}

export default function ProposalModal({ caseId, open, onClose }: IProposalModal) {
  const [loading, setLoading] = useState(true);
  const { token, loggedUser } = useContext(AuthContext);
  const [hasProposal, setHasProposal] = useState(false);

  const axiosInstance = axios.create({
    baseURL: process.env.JUSTICE_FOR_ALL_API_URL,
    headers: { Authorization: `Basic ${token}` }
  })

  const handleSendProposal = async () => {
    try {
      const res = await axiosInstance.post(`/proposal/case/${caseId}`)
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
  
  const handleGetProposal = async () => {
    setLoading(true);
    try {
      const res = await axiosInstance.get(`/proposal/case/${caseId}`);
      if (res.status === 200) {
        console.log(res.data)
        const proposal = res.data.proposals.some((p: IProposal) => p.lawyerId === loggedUser?.userId);
        setHasProposal(proposal);
      }
    } catch(err) {
      console.error('Error to find proposal: ', err)
      alert('Erro ao buscar proposta')
      onClose()
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    if (caseId) {
      handleGetProposal()
    }
  }, [caseId])

  return (
    <Modal open={open} onClose={onClose}>
      <ModalBox display="flex" flexDirection="column" rowGap="1rem">
        {loading ? <CircularProgress /> : (
          hasProposal ?
          <Typography>Você já enviou uma proposta</Typography> : 
          <>
            <Typography>Deseja enviar uma proposta para ser a pessoa responsável por esse caso?</Typography>
            <Box display="flex" flexDirection="row" columnGap="1rem">
              <Button variant="outlined" onClick={handleSendProposal}>Sim</Button>
              <Button variant="contained" onClick={onClose}>Cancelar</Button>
            </Box>
          </>
        )}
      </ModalBox>
    </Modal>
  )
}