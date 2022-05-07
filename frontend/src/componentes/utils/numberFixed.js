export function number2Fixed(number) {
  if (typeof number === "number" && number % 1 !== 0) {
    return number.toFixed(2);
  }
  return number;
}
