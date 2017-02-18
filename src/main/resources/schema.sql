CREATE TABLE IF NOT EXISTS persistent_logins (
  username  VARCHAR(64) NOT NULL,
  series    VARCHAR(64) NOT NULL PRIMARY KEY,
  token     VARCHAR(64) NOT NULL,
  last_used TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE OR REPLACE FUNCTION update_changetimestamp_column()
  RETURNS TRIGGER AS $$
BEGIN
  NEW.last_used = now();
  RETURN NEW;
END;
$$ language 'plpgsql';
DROP TRIGGER update_ab_changetimestamp ON persistent_logins;
CREATE TRIGGER update_ab_changetimestamp BEFORE UPDATE
  ON persistent_logins FOR EACH ROW EXECUTE PROCEDURE
  update_changetimestamp_column();