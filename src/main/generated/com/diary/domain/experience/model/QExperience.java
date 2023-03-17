package com.diary.domain.experience.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExperience is a Querydsl query type for Experience
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExperience extends EntityPathBase<Experience> {

    private static final long serialVersionUID = -24780991L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExperience experience = new QExperience("experience");

    public final com.diary.common.base.QBaseEntity _super = new com.diary.common.base.QBaseEntity(this);

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.diary.domain.post.model.QPost post;

    //inherited
    public final EnumPath<com.diary.common.base.BaseStatus> status = _super.status;

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QExperience(String variable) {
        this(Experience.class, forVariable(variable), INITS);
    }

    public QExperience(Path<? extends Experience> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExperience(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExperience(PathMetadata metadata, PathInits inits) {
        this(Experience.class, metadata, inits);
    }

    public QExperience(Class<? extends Experience> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new com.diary.domain.post.model.QPost(forProperty("post"), inits.get("post")) : null;
    }

}

