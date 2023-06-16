import React, { useState } from 'react'
import { Box, IconButton, InputBase, Paper, TextField } from "@mui/material";
import SearchIcon from '@mui/icons-material/Search';

interface ISearchInputProps {
  onSearch: (value: string) => void
}

export default function SearchInput({ onSearch }: ISearchInputProps) {
  const [value, setValue] = useState('');

  return (
    <Box>
      <TextField size='small' onChange={(event) => setValue(event.target.value)} />
      <IconButton type="button" sx={{ p: '10px' }} aria-label="search" onClick={() => onSearch(value)}>
        <SearchIcon />
      </IconButton> 
    </Box>
  )
}