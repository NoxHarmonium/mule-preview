/**
 * Functions to get the state of the Bitbucket UI
 */

export const isRunningInBitbucket = () => {
  const metaTag = document.querySelector("meta[name=application-name]");
  return metaTag === null
    ? false
    : metaTag.getAttribute("content") === "Bitbucket";
};

export const getBitbucketDiffElement = () =>
  document.querySelector(".diff-view");

export const getBitbucketFilePreviewElement = () =>
  document.querySelector(".source-view");

export const getCurrentFile = () => {
  const filePathObj = window.wrappedJSObject
    .require("bitbucket/internal/model/page-state")
    .getFilePath();
  return filePathObj.attributes.components.join("/");
};

export const getFileRawUrlFromContentView = () => {
  // This is not the best but it works for now I suppose
  return document.URL.replace("/browse/", "/raw/");
};
