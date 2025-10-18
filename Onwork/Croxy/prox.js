import axios from "axios";

// https://43.129.176.186/38909154750448fcb833d9d63d9840da/_rhsfy-BG://PQZBNP.QYZ/OytdKbGV

async function headRequest() {
  const base = 'https://43.129.176.186';
  const prefix = '/38909154750448fcb833d9d63d9840da/';
  const weird = '_rhsfy-BG://PQZBNP.QYZ/OytdKbGV';
  const url = base + prefix + weird;

  console.log(url)

  try {
    const res = await axios.head(url, { timeout: 10000 });
    console.log(res.headers);
  } catch (err) {
    console.error(err.toString());
  }
}

headRequest();
