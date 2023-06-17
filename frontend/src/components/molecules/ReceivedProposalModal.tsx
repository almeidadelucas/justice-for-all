import React, { useContext, useEffect, useState } from "react";
import { Box, Modal, Typography } from "@mui/material";
import { ModalBox } from "../atoms/ModalBox";
import axios from "axios";
import { AuthContext } from "@/context/AuthContext";
import ProposalCard from "./ProposalCard";

interface IReceivedProposalModal {
  caseId?: number;
  open: boolean;
  onClose: () => void;
}

export interface IProposal {
  lawyerId: number;
  lawyerFirstName: string;
  lawyerLastName: string;
  proposalDate: string;
  oab: string;
}

export default function ReceivedProposalModal({ caseId, open, onClose }: IReceivedProposalModal) {
  const { token } = useContext(AuthContext);

  const [proposals, setProposals] = useState<IProposal[]>([]);

  const axiosInstance = axios.create({
    baseURL: process.env.JUSTICE_FOR_ALL_API_URL,
    headers: { Authorization: `Basic ${token}` }
  })

  useEffect(() => {
    if (caseId) {
      axiosInstance.get(`/proposal/case/${caseId}`)
      .then(res => {
        setProposals(res.data.proposals)
      })
      .catch(err => {
        alert('Erro ao buscar propostas');
        console.error('Error to get proposals', err)
        onClose();
      })
    }
  }, [caseId])

  return (
    <Modal open={open} onClose={onClose}>
      <ModalBox display="flex" flexDirection="column" rowGap="1rem">
        <Typography>{proposals.length > 0 ? 'Qual proposta deseja aceitar?' : 'Nenhum proposta recebida'}</Typography>
        <Box display="flex" flexDirection="row" rowGap="gap" gap="2rem">
          {proposals.map(proposal => <ProposalCard key={proposal.lawyerId} proposal={proposal} caseId={caseId as number} onAccept={onClose} />)}
        </Box>
      </ModalBox>
    </Modal>
  )
}