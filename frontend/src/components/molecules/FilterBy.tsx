import React, { useState } from 'react';
import { Box, MenuItem, Select, SelectChangeEvent } from "@mui/material";
import SearchInput from "./SearchInput";

const NONE_OPTION = { key: 'none', label: 'Nenhum' }

export function FilterBy({ options, onSearch }: { options: { key: string; label: string }[], onSearch: (key: string, value: string) => void}) {
  const [key, setKey] = useState('');

  return (
    <Box sx={{
      display: "flex",
      flexDirection: "row",
      columnGap: '1rem'
    }}>
      <Select
        size="small"
        onChange={(event: SelectChangeEvent) => {
          const value = event.target.value
          setKey(value === NONE_OPTION.key ? '' : value)
        }}
        defaultValue={NONE_OPTION.key}
      >
        {[NONE_OPTION, ...options].map(({ key, label }) => (
          <MenuItem key={key} value={key}>{label}</MenuItem>
        ))}
      </Select>
      <SearchInput onSearch={(value) => onSearch(key, value)}/>
    </Box>
  )
}