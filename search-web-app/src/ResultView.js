import React from "react";

export default ({ result }) => (
  <li className="sui-result">
    <div className="sui-result__header">
      <span
        className="sui-result__title"
        // Snippeted results contain search term highlights with html and are
        // 100% safe and santitized, so we dangerously set them here
        dangerouslySetInnerHTML={{ __html: result.name.snippet }}
      />
    </div>
    <div className="sui-result__body">
      <div
        className="sui-result__image"
        style={{
          maxWidth: "140px",
          paddingLeft: "24px",
          paddingTop: "10px"
        }}
      >
        <img
          src={result.image_url.raw}
          alt="thumb"
          style={{
            display: "block",
            width: "100%",
            height: "100%",
            objectFit: "cover",
            objectPosition: "center"
          }}
        />
      </div>
      <ul className="sui-result__details">
        <li>
          <span className="sui-result__key">Genre</span>{" "}
          <span
            className="sui-result__value"
            dangerouslySetInnerHTML={{
              __html: result.genre.snippet
            }}
          />
        </li>
        <li>
          <span className="sui-result__key">Publisher</span>{" "}
          <span
            className="sui-result__value"
            dangerouslySetInnerHTML={{
              __html: result.publisher.snippet
            }}
          />
        </li>
        <li>
          <span className="sui-result__key">Critic Score</span>{" "}
          <span className="sui-result__value">{result.critic_score.raw}</span>
        </li>
        <li>
          <span className="sui-result__key">User Score</span>{" "}
          <span className="sui-result__value">{result.user_score.raw}</span>
        </li>
        <li>
          <span className="sui-result__key">Platform</span>{" "}
          <span
            className="sui-result__value"
            dangerouslySetInnerHTML={{
              __html: result.platform.snippet
            }}
          />
        </li>
      </ul>
    </div>
  </li>
);
