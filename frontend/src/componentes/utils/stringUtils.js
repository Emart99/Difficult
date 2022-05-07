import { upperFirst } from "lodash";
export function firstUpper(string) {
  if (typeof string === "string") {
    const min = string.toLowerCase();
    return upperFirst(min);
  }
  return string;
}
