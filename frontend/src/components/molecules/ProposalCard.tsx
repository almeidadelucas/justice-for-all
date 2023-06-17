import React, { useContext } from "react";
import { Box, Button, Chip, Typography } from "@mui/material";
import axios from "axios";
import { AuthContext } from "@/context/AuthContext";

interface IProposal {
  lawyerId: number;
  lawyerFirstName: string;
  lawyerLastName: string;
  proposalDate: string;
  oab: string;
}

interface IProposalCardProps {
  caseId: number;
  proposal: IProposal;
  onAccept: () => void;
}

export default function ProposalCard({ caseId, proposal, onAccept }: IProposalCardProps) {
  const { token } = useContext(AuthContext);

  const handleAccept = () => {
    try {
      axios.post(`${process.env.JUSTICE_FOR_ALL_API_URL}/proposal/match`, { caseId, lawyerId: proposal.lawyerId }, {headers: { Authorization: `Basic ${token}` }})
      onAccept();
    } catch(err) {
      console.error('Error to accept proposal', err);
      alert('Erro ao aceitar proposta')
    }
  }

  return (
    <Box
      sx={{
        padding: '1rem',
        border: '1px solid black',
        borderRadius: '10px',
        backgroud: '#a8a7a7',
        display: 'flex',
        flexDirection: 'column',
        rowGap: '0.5rem'
      }}
    >
      <Box>
        <Chip label={proposal.oab} />
      </Box>
      <Box display="flex" flexDirection="row" alignItems="center" columnGap="0.25rem">
        <Typography fontWeight="bold">Advogado(a):</Typography>
        <Typography>{`${proposal.lawyerFirstName} ${proposal.lawyerLastName}`}</Typography>
      </Box>
      <Box display="flex" flexDirection="row" alignItems="center" columnGap="0.25rem">
        <Typography fontWeight="bold">Data de solicitação:</Typography>
        <Typography>{proposal.proposalDate.split('T')[0]}</Typography>
      </Box>
      <Button variant="outlined" onClick={handleAccept}>Aceitar Proposta</Button>
    </Box>
  )
}