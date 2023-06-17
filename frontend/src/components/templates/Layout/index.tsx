import React, { useContext, useRef } from 'react';
import { AppBar, Box, Button, IconButton, Toolbar } from '@mui/material';
import { AuthContext } from '@/context/AuthContext';
import { useRouter } from 'next/router';
import LogoutIcon from '@mui/icons-material/Logout';

export function Layout({ children }: { children: React.ReactNode }) {
  const { isLogged, logout } = useContext(AuthContext);
  const { push } = useRouter();
  const ref = useRef<HTMLDivElement>(null);

  return (
    <>
      {isLogged && (
        <Box sx={{ flexGrow: 1 }} ref={ref}>
          <AppBar position="static">
            <Toolbar>
              <Button color="inherit" onClick={() => push('/')}>
                Home
              </Button>
              <Button color="inherit" onClick={() => push('/show_cases')}>
                Listagem de casos
              </Button>
              <Box sx={{ flexGrow: 1 }} />
              <IconButton color="inherit" onClick={logout}>
                <LogoutIcon />
              </IconButton>
            </Toolbar>
          </AppBar>
        </Box>
      )}
      <Box
        sx={{
          width: '100vw',
          minHeight: `calc(100vh - ${ref.current?.clientHeight || 0}px)`,
          display: 'flex',
          padding: '2rem 4rem ',
        }}
      >
        {children}
      </Box>
    </>
  );
}
