/* eslint-disable no-undef */
/* eslint-disable @typescript-eslint/no-var-requires */
require('dotenv').config();

module.exports = {
	reactStrictMode: true,
	env: {
		// eslint-disable-next-line no-undef
		JUSTICE_FOR_ALL_API_URL: process.env.JUSTICE_FOR_ALL_API_URL,
	},
};
