import React from "react";
import { Box, Stack, Switch, Typography } from "@mui/material";

interface ISwitchLawyerVision {
  checked: boolean;
  handleChange: (value: boolean) => void;
}

export default function SwitchLawyerVision({ checked, handleChange }: ISwitchLawyerVision) {
  return (
    <Stack direction="row" spacing={1} alignItems="center" marginBottom="1rem">
      <Box display="flex" alignItems="center" border="1px solid black" padding="0.5rem 1rem" borderRadius="15px">
        <Typography>Meus Casos</Typography>
        <Switch
          checked={checked}
          onChange={(event) => {
            handleChange(event.target.checked)
          }}
          inputProps={{ 'aria-label': 'controlled' }}
        />
        <Typography>Casos Abertos</Typography>
      </Box>
    </Stack>
  )
}
