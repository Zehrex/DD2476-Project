1
https://raw.githubusercontent.com/falvojr/stackoverflow-61560293/master/src/main/java/com/falvojr/domain/LogRepository.java
package com.falvojr.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends MongoRepository<Log, ObjectId> {

}
