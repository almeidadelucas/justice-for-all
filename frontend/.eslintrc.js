module.exports = {
  root: true,
  parser: '@typescript-eslint/parser',
  parserOptions: {
    ecmaVersion: 2021,
    sourceType: 'module',
    ecmaFeatures: {
      jsx: true,
    },
  },
  settings: {
    react: {
      version: 'detect',
    },
  },
  env: {
    browser: true,
    node: true,
  },
  extends: [
    'eslint:recommended',
    'plugin:react/recommended',
    'plugin:@typescript-eslint/recommended',
    'plugin:prettier/recommended',
  ],
  rules: {
    quotes: ['error', 'single'],
    'prettier/prettier': [
      'error',
      {
        singleQuote: true,
      },
    ],
    // Aqui você pode adicionar ou modificar as regras do ESLint conforme necessário
    // Consulte a documentação do ESLint para mais informações: https://eslint.org/docs/rules/
  },
};
