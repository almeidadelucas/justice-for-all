import React from "react";
import { Stack, Switch, Typography } from "@mui/material";

interface ISwitchLawyerVision {
  checked: boolean;
  handleChange: (value: boolean) => void;
}

export default function SwitchLawyerVision({ checked, handleChange }: ISwitchLawyerVision) {
  return (
    <Stack direction="row" spacing={1} alignItems="center">
      <Typography>Meus Casos</Typography>
      <Switch
        checked={checked}
        onChange={(event) => {
          handleChange(event.target.checked)
        }}
        inputProps={{ 'aria-label': 'controlled' }}
      />
      <Typography>Casos Abertos</Typography>
    </Stack>
  )
}
