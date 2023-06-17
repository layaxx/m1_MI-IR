function toBinary(input) {
  return (input >>> 0).toString(2)
}

function toBinaryPadded(input) {
  let binaryString = toBinary(input)

  // add padding
  const paddingNeeded = 8 - (binaryString.length % 8)
  if (paddingNeeded !== 8)
    binaryString =
      Array.from({ length: paddingNeeded }).fill("0").join("") + binaryString

  let result = ""
  // add separators
  const sepsNeeded = Math.floor((binaryString.length - 1) / 8)
  for (let i = 0; i < sepsNeeded; i++) {
    result += binaryString.substring(i * 8, i * 8 + 8) + "_"
  }
  result += binaryString.substring(binaryString.length - 8, binaryString.length)
  return result
}

function binaryPaddedToInteger(padded) {
  return padded.split("_").map((str) => Number.parseInt(str, 2))
}

function bytesNeeded(input) {
  if (input < 0) throw new Error("not implemented for negative numbers")

  for (let i = 1; i <= 10; i++) if (input < 2 ** (7 * i)) return i

  throw new Error("not implemented for numbers > 2**70")
}

function toVByte(input) {
  const bytesNeededForInput = bytesNeeded(input)

  if (bytesNeededForInput === 1) return 2 ** 7 + input

  const expo = 8 * (bytesNeededForInput - 1) - 1

  return (
    toVByte(input % 2 ** expo) +
    (Math.floor(input / 2 ** expo) << (expo + bytesNeededForInput - 1))
  )
}

const input = [34312, 80, 899875, 2225, 1024, 7, 998]

for (const number of input) {
  const padded = toBinaryPadded(toVByte(number))
  console.log({ number, binary: padded, int: binaryPaddedToInteger(padded) })
}
