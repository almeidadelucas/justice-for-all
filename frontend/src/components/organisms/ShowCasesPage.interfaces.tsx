export const CLIENT_FILTERS = [
  { key: 'lawyerId', label: 'ID do advogado' },
  { key: 'category', label: 'Categoria' },
  { key: 'description', label: 'Descrição' },
]

export const LAWYER_FILTERS = [
  { key: 'userId', label: 'ID do cliente' },
  { key: 'category', label: 'Categoria' },
  { key: 'description', label: 'Descrição' },
]

const Allegation = {
	INNOCENT: 'INNOCENT',
	GUITY: 'GUITY'
}

export const UserTypes = {
  LAWYER: 'LAWYER',
  CLIENT: 'CLIENT'
}

export const Visions = {
  LAWYER_CASES: 'LAWYER_CASES',
  OPENED_CASES: 'OPENED_CASES',
  CLIENT_CASES: 'CLIENT_CASES'
}

export type TVisions = keyof typeof Visions

export interface ICase {
	caseId: number;
  userId: number;
  lawyerId: number;
  title: string;
  category: string;
  description: string;
  alegation: keyof typeof Allegation;
  evidencesPDF: string;
  evidencesImages: string;
  caseIdentifier: string;
  open: boolean;
}
