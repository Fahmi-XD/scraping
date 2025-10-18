import axios from "axios";
import crypto from "crypto";

class Util {
  static log = false
  
  static logMsg(processName, msg) {
    if (this.log) {
      console.log(`[${processName}] ${msg}`);
    }
  }

  // Todo ...
}

async function placeholder(params, log) {
  try {
    Util.log = log
    
    // Todo ...
  } catch (error) {
    console.error()
  }
}

/**
 * Contoh
 */
(async () => {
  const response = await placeholder({}, true)
  console.log(response)
});