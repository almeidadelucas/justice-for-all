import React from "react";
import { Box } from "@mui/material";
import { CreateCaseForm } from "../molecules/CreateCaseForm";

export default function EditCasePage() {
	return (
		<Box
        sx={{
          display: 'flex',
          flexDirection: 'column',
          justifyContent: 'center',
          alignItems: 'center',
          rowGap: '2rem',
          width: '100%'
        }}>
			<CreateCaseForm />
		</Box>
	);
}