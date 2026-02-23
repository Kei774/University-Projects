export default {
  testEnvironment: "jsdom",
  setupFilesAfterEnv: ["<rootDir>/src/setupTests.cjs"],
  moduleNameMapper: {
    "\\.(css|jpg|png)$": "<rootDir>/src/__mocks__/fileMock.js",
  },
  transform: {
    "^.+\\.[jt]sx?$": "babel-jest",
  },
};
