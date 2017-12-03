<?xml version="1.0" encoding="UTF-8"?>  
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${beanId}">
    <resultMap id="BaseResultMap" type="${beanpackage}.${beanName}" >
		<id column="${ormFields.pk.coloum}" property="${ormFields.pk.coloum}" jdbcType="${ormFields.pk.ormDbtype}" />
		<#list ormFields.fields as a>
			<result column="${a.coloum}" property="${a.coloum}" jdbcType="${a.ormDbtype}" />
		</#list>
  	</resultMap>
  
  <sql id="Base_Column_List" >
	 ${dbfields}
  </sql>
  
  <!-- 分页获取数据 -->
  <select id="queryPageMap" resultMap="BaseResultMap" parameterType="java.util.HashMap">
  		select
  		<include refid="Base_Column_List"></include>
  		from ${tableName} where 1=1
  		
 		<if test="${ormFields.pk.querycondition}" > and ${ormFields.pk.coloum} 
			<#if  ormFields.pk.combar = 'like'  > 
				like CONCAT ('%',${r"#{"}${queryField.coloum}${r"}"},'%')
			<#else>
			 	${ormFields.pk.combar} ${r"#{"}${ormFields.pk.coloum}${r"}"}
			</#if>
		</if> 		
  		
  		<#list ormFields.fields as queryField>
  			<if test="${queryField.querycondition}" > and ${queryField.coloum} 
  				<#if  queryField.combar = 'like'  > 
  					like CONCAT ('%',${r"#{"}${queryField.coloum}${r"}"},'%')
  				<#else>
  				 	${queryField.combar} ${r"#{"}${queryField.coloum}${r"}"}
  				</#if>
  			</if>
  		</#list>
  		limit ${r"#{startnum}"},${r"#{pageSize}"}
  </select>
  
  <!-- 分页获取数据总条数 -->
  <select id="queryCount" parameterType="java.util.HashMap" resultType="Integer">
	   SELECT count(1) FROM ${tableName} 
		WHERE 1=1
  		<if test="${ormFields.pk.querycondition}" > and ${ormFields.pk.coloum} 
			<#if  ormFields.pk.combar = 'like'  > 
				like CONCAT ('%',${r"#{"}${queryField.coloum}${r"}"},'%')
			<#else>
			 	${ormFields.pk.combar} ${r"#{"}${ormFields.pk.coloum}${r"}"}
			</#if>
 		</if> 		
  		
  		<#list ormFields.fields as queryField>
  			<if test="${queryField.querycondition}" > and ${queryField.coloum} 
  				<#if  queryField.combar = 'like'  > 
  					like CONCAT ('%',${r"#{"}${queryField.coloum}${r"}"},'%')
  				<#else>
  				 	${queryField.combar} ${r"#{"}${queryField.coloum}${r"}"}
  				</#if>
  			</if>
  		</#list>
  </select>
  
  <select id="queryById" parameterType="${ormFields.pk.javaType}" resultMap="BaseResultMap">
  		SELECT
		   <include refid="Base_Column_List"></include>
		FROM
		${tableName}
		where ${ormFields.pk.coloum}=${r"#{id}"}
  </select>
  
  <!-- 添加数据 -->
  <insert id="add" parameterType="${beanpackage}.${beanName}">
  		INSERT INTO ${tableName}(
  			<#list ormFields.insertFields as attr>
  				${r"#{"}${attr.coloum}${r"}"}
  				<#if attr_index lt (ormFields.insertFields?size-1)>
  					,
  				</#if>
  			</#list>
  		) values(
  			<#list ormFields.insertFields as attr>
  				${r"#{"}${attr.coloum}${r"}"}
  				<#if attr_index lt (ormFields.insertFields?size-1)>
  					,
  				</#if>
  			</#list>
  		)
  </insert>
  
  <!-- 删除数据 -->
  <delete id="delById" parameterType="${ormFields.pk.javaType}"> 
  		DELETE FROM ${tableName} WHERE ${ormFields.pk.coloum}=${r"#{id}"}
  </delete>
  
  
  <update id="update" parameterType="${beanpackage}.${beanName}">
  		UPDATE ${tableName} set
  			<#list ormFields.insertFields as attr>
  				${attr.coloum} = ${r"#{"}${attr.coloum}${r"}"}
  				<#if attr_index lt (ormFields.insertFields?size-1)>
  					,
  				</#if>
  			</#list>
  		WHERE ${ormFields.pk.coloum}=${r"#{"}${ormFields.pk.coloum}${r"}"}
  </update>
</mapper>