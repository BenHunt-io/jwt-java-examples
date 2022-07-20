# Example Application

This example application is a public bank. The bank has different resources that should be made available to different people depending on their level of access.

### Resources:

- Balance Sheet
  - Accessible by: roles: all
- Checking Acccount:
  - Accessible by: roles: {tellers, admin} and logic: account owner
- Employee Performance Review
  - Accessible by: logic: employee's manager

# JWT Overview

<b>JWT:</b> `{header}.{payload}.{signature}`

JWT in JSON:

```
// Header
{
  "alg": "HS256",
  "typ": "JWT"
}
// Payload
{
  "sub": "1234567890",
  "name": "John Doe",
  "iat": 1516239022
}
```

Base 64 Encoded JWT:

```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.
eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
```

## Header

Meta-data describing the token. Primarily is used to specify the algo.

```
{
    "alg": "ES256",
    // Other less-common claims may be present.
    "kid" : "5" // identifies key used to sign in multi-key scheme
}
```

## Payload

Contains claims about the user. Claims are assertions made about an entity, be it a person or an object.

<b> Registered Claims: </b>

```
{
    "sub": "therealbenji",
    "iss": "abc.com",
    "exp" "1658239245",
    "nbf" "1658238000",
}
```

<b> Private Claims: </b> Application defined claims

<b> Public Claims: </b> Application defined claims that are registered publicly

## JSON Web Signatures

Authenticity of data. Guarantees data contained with JWT has not been tampered with.

<b>Signing Algos</b>

- HMAC (HS256): Symmetric Key Authentication
  - 1-1
- RSASSA PKCS1 v1.5 (RS256) : Asymmetric Key Authentication
  - 1-many
- ECDSA (ES256) : Effecient Asymmetric Key Authentication
  - 1-many
