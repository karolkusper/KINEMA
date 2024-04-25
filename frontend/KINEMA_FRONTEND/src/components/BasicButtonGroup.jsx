import * as React from 'react';
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';
import {Link} from 'react-router-dom'

export default function BasicButtonGroup() {
  return (
    <ButtonGroup variant="contained" aria-label="Basic button group">
      <Link to="/"><Button>Home</Button></Link>
      <Link to="/movies"><Button>Movies</Button></Link>
      <Link to="/about"><Button>About</Button></Link>
      <Link to="/contact"><Button>Contact</Button></Link>
    </ButtonGroup>
  );
}