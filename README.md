# OpenAPI Generator - `java-wiremcok` generator demo

This project covers:
- Why ? The problem it solves
- How ? How to use the `java-wiremock` generator
- When ? The uses cases and situations where it's useful

## Why ?

The generator aims to solve multiples issues when creating stubs for APIs you're consuming.

1. **Misimplementation** - You may wrongly implement your stub (missing header, wrong status code, etc.)
2. **Out of date** - You'll have to keep the stubs up to date with the latest changes (new mandatory query parameter, removed header, etc.)
3. **Too many endpoints** - If you want to test all the possible use cases from the remote API, you'll have to create a stub for each endpoint and each status code.

## How ?

See the related pull requests:
- [fix: ExampleGenerator correctly produces YYYY-MM-dd format for date with examples](https://github.com/OpenAPITools/openapi-generator/pull/17495)
- [fix: ExampleGenerator correctly generates allOf composed schemas](https://github.com/OpenAPITools/openapi-generator/pull/17499)
- [fix: DefaultCodegen now generates an exemple for each status codes](https://github.com/OpenAPITools/openapi-generator/pull/17603)
- [feat: add java-wiremock generator #17614](https://github.com/OpenAPITools/openapi-generator/pull/17614)

TODO: Add how to use it with code

## When ?

### 1. When writing an integration test

Whenever you write an integration test with an API **you should mock the server**.

You should mock the server to properly ensure correctness of:
- path parameters/query parameters
- headers (authorization, content type, accept, etc.)
- serialization/deserialization

Mocking the client tides you to:
- client internals
  - exceptions
  - fault
  - etc.
- the client itself

### 2. When distributing a Client SDK 

When you distribute a Client SDK, you can provide to your users a test package which allows them to quickly setup mock expectations.

### 3. When testing legacy clients

Go back to point 1, but this time, the client is already there and might be... TODO: Complete this