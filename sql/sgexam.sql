/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : sgexam

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2018-03-08 11:06:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `announcement`
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement` (
  `A_ID` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '公告id',
  `A_Title` varchar(50) NOT NULL COMMENT '标题',
  `A_Content` varchar(500) NOT NULL COMMENT '内容',
  `A_Author` char(16) NOT NULL COMMENT '作者id',
  `A_StartTime` date NOT NULL COMMENT '有效开始时间',
  `A_EndTime` date DEFAULT NULL COMMENT '有效结束时间',
  `A_CreateDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `A_AuthorName` varchar(20) DEFAULT NULL COMMENT '作者名',
  PRIMARY KEY (`A_ID`,`A_Content`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of announcement
-- ----------------------------

-- ----------------------------
-- Table structure for `classes`
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `CLS_ID` char(16) NOT NULL COMMENT '班级id',
  `CLS_Name` varchar(30) NOT NULL COMMENT '班级名',
  `COLL_ID` char(16) NOT NULL COMMENT '学院id',
  PRIMARY KEY (`CLS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classes
-- ----------------------------
INSERT INTO `classes` VALUES ('2013001', '信息管理1班', '20130');
INSERT INTO `classes` VALUES ('2013002', '软件工程1班', '20130');
INSERT INTO `classes` VALUES ('2013101', '电气班', '20131');
INSERT INTO `classes` VALUES ('2013201', '英语1班', '20132');

-- ----------------------------
-- Table structure for `college`
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college` (
  `COLL_ID` char(16) NOT NULL COMMENT '学院id',
  `COLL_Name` varchar(30) NOT NULL COMMENT '学院名称',
  PRIMARY KEY (`COLL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES ('20130', '信息科学与工程学院');
INSERT INTO `college` VALUES ('20131', '物理与电子工程学院');
INSERT INTO `college` VALUES ('20132', '外语学院');

-- ----------------------------
-- Table structure for `count`
-- ----------------------------
DROP TABLE IF EXISTS `count`;
CREATE TABLE `count` (
  `p_id` varchar(16) NOT NULL COMMENT '试卷编号',
  `p_name` varchar(30) DEFAULT NULL COMMENT '试卷名称',
  `allpeople` int(6) DEFAULT NULL COMMENT '总人数',
  `nopass` int(6) DEFAULT NULL COMMENT '未及格',
  `pass` int(6) DEFAULT NULL COMMENT '及格',
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of count
-- ----------------------------
INSERT INTO `count` VALUES ('91b894363d854104', '大学物理考试', '1', '1', '0');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `C_ID` char(16) NOT NULL,
  `C_Name` varchar(30) NOT NULL,
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '离散数学');
INSERT INTO `course` VALUES ('2', '大学物理');
INSERT INTO `course` VALUES ('3', '大学数学');
INSERT INTO `course` VALUES ('4', '计算机组成原理');

-- ----------------------------
-- Table structure for `grades`
-- ----------------------------
DROP TABLE IF EXISTS `grades`;
CREATE TABLE `grades` (
  `G_ID` char(16) NOT NULL COMMENT '成绩id',
  `T_ID` char(16) NOT NULL COMMENT '考试id',
  `C_ID` char(16) NOT NULL COMMENT '课程id',
  `grades` int(3) DEFAULT NULL COMMENT '成绩',
  PRIMARY KEY (`G_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grades
-- ----------------------------

-- ----------------------------
-- Table structure for `manager`
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `M_ID` char(16) NOT NULL COMMENT '管理员id',
  `M_Pass` varchar(20) NOT NULL COMMENT '管理员密码',
  `M_Name` varchar(12) NOT NULL COMMENT '管理员真实姓名',
  `M_LastLoginTime` datetime DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`M_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('admin', 'admin', 'admin', '2017-04-23 10:14:30');

-- ----------------------------
-- Table structure for `paper`
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper` (
  `p_id` char(16) NOT NULL COMMENT '试卷id',
  `c_id` varchar(50) DEFAULT NULL COMMENT '课程id',
  `p_name` varchar(30) DEFAULT NULL COMMENT '试卷名称',
  `q_id` varchar(500) DEFAULT NULL COMMENT '试题id',
  `p_scores` float DEFAULT NULL COMMENT '总分',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createUser` varchar(16) DEFAULT NULL COMMENT '创建人id',
  `updateUser` varchar(16) DEFAULT NULL COMMENT '修改人id',
  `startTime` datetime DEFAULT NULL COMMENT '考试开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '考试结束时间',
  `CLS_ID` varchar(16) DEFAULT NULL COMMENT '班级id',
  PRIMARY KEY (`p_id`),
  KEY `p_scores` (`p_scores`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper
-- ----------------------------
INSERT INTO `paper` VALUES ('91b894363d854104', '1', '大学物理考试', '4b13a98d871340fd@4dac18332a144c85@98f8bee242c44841@bfe216682566429e@d00e94ffe6eb4865', '18', '2017-04-23 09:07:58', '2017-04-23 09:11:48', 'admin', '李老师', '2017-04-23 09:00:00', '2017-04-23 11:00:00', '2013002');

-- ----------------------------
-- Table structure for `q5answer`
-- ----------------------------
DROP TABLE IF EXISTS `q5answer`;
CREATE TABLE `q5answer` (
  `q_id` varchar(16) NOT NULL COMMENT '题目id',
  `q_title` varchar(500) NOT NULL COMMENT '题目问题',
  `answer` varchar(1000) DEFAULT NULL COMMENT '答案',
  `s_id` varchar(16) NOT NULL COMMENT '学生id',
  `sc_id` varchar(16) NOT NULL COMMENT '成绩id',
  `rightanswer` varchar(1000) DEFAULT NULL COMMENT '参考答案',
  `q_score` float DEFAULT NULL COMMENT '题目分值',
  PRIMARY KEY (`q_id`,`sc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of q5answer
-- ----------------------------
INSERT INTO `q5answer` VALUES ('bfe216682566429e', '万有引力发现借鉴了前人哪些成果？牛顿的自然哲学思想是什么？', '123123', '13115011019', '42cc3c1321324277', '伽利略、笛卡尔——惯性定律  开普勒——开普勒第一、第二和第三定律  法则一除那些真实而已足够说明其现象者外，不必再去寻求自然界事物的其他原因 法则二所以对于自然界中同一类结果，必须尽可能归之于同一种原因 法则三物体的属性，凡是即不能增强也不能减弱者，又为我们实验所能及的范围的一切物体所具有者，就应视为所有物体的普遍属性 法则四在实验哲学中，我们必须把那些从各种现象中运用一般归纳而导出的命题看做是完全正确的或很接近于真实的，虽然可以想象出任何相反的假说，但是在没有出现其他现象足以使之更为正确或者出现例外之前，仍然应当给予如此的对待。', '10');

-- ----------------------------
-- Table structure for `question`
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `Q_ID` char(16) NOT NULL COMMENT '题目id',
  `Q_Type` char(3) NOT NULL COMMENT '题目类型',
  `Q_Title` varchar(200) DEFAULT NULL COMMENT '题目标题',
  `Q_Select` varchar(200) DEFAULT NULL COMMENT '题目选项',
  `Q_Score` float NOT NULL COMMENT '题目分值',
  `Q_Answer` varchar(1000) NOT NULL COMMENT '答案',
  `course_id` varchar(10) DEFAULT NULL COMMENT '课程id',
  `Q_Explaination` varchar(1000) DEFAULT NULL COMMENT '答案解释',
  PRIMARY KEY (`Q_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('4b13a98d871340fd', 'q2', '一定量的理想气体，分别进行如题5.1.4图所示的两个卡诺循环abdca和a′b′c′d′a′.若在pV图上这两个循环曲线所围面积相等，则可以由此得知这两个循环', '效率相等@由高温热源处吸收的热量相等@在低温热源处放出的热量相等@在每次循环中对外做的净功相等', '3', 'AB', '2', 'AB');
INSERT INTO `question` VALUES ('4dac18332a144c85', 'q1', '一个质点在做圆周运动时,则有', '切向加速度一定改变,法向加速度也改变@切向加速度可能不变,法向加速度一定改变@切向加速度可能不变,法向加速度不变@切向加速度一定改变,法向加速度不变', '2', 'B', '2', '加速度的切向分量aｔ起改变速度大小的作用,而法向分量an起改变速度方向的作用．质点作圆周运动时,由于速度方向不断改变,相应法向加速度的方向也在不断改变,因而法向加速度是一定改变的．至于aｔ是否改变,则要视质点的速率情况而定．质点作匀速率圆周运动时, aｔ恒为零；质点作匀变速率圆周运动时, aｔ为一不为零的恒量,当aｔ改变时,质点则作一般的变速率圆周运动．由此可见,应选(B)．');
INSERT INTO `question` VALUES ('98f8bee242c44841', 'q4', '质点速度大，加速度就一定大', null, '1', '0', '2', '');
INSERT INTO `question` VALUES ('bfe216682566429e', 'q5', '万有引力发现借鉴了前人哪些成果？牛顿的自然哲学思想是什么？', null, '10', '伽利略、笛卡尔——惯性定律  开普勒——开普勒第一、第二和第三定律  法则一除那些真实而已足够说明其现象者外，不必再去寻求自然界事物的其他原因 法则二所以对于自然界中同一类结果，必须尽可能归之于同一种原因 法则三物体的属性，凡是即不能增强也不能减弱者，又为我们实验所能及的范围的一切物体所具有者，就应视为所有物体的普遍属性 法则四在实验哲学中，我们必须把那些从各种现象中运用一般归纳而导出的命题看做是完全正确的或很接近于真实的，虽然可以想象出任何相反的假说，但是在没有出现其他现象足以使之更为正确或者出现例外之前，仍然应当给予如此的对待。', '2', '');
INSERT INTO `question` VALUES ('d00e94ffe6eb4865', 'q3', '用波长为λ的单色光垂直入射在缝宽a＝4λ的单缝上，对应衍射角为30°的衍射光，单缝可以划分为____个半波带。', null, '2', '2', '2', 'asdasd');

-- ----------------------------
-- Table structure for `questiontype`
-- ----------------------------
DROP TABLE IF EXISTS `questiontype`;
CREATE TABLE `questiontype` (
  `id` char(3) NOT NULL COMMENT '试题类型id',
  `qt_name` varchar(30) NOT NULL COMMENT '试题类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of questiontype
-- ----------------------------
INSERT INTO `questiontype` VALUES ('q1', '单选题');
INSERT INTO `questiontype` VALUES ('q2', '多选题');
INSERT INTO `questiontype` VALUES ('q3', '填空题');
INSERT INTO `questiontype` VALUES ('q4', '判断题');
INSERT INTO `questiontype` VALUES ('q5', '主观题');

-- ----------------------------
-- Table structure for `scores`
-- ----------------------------
DROP TABLE IF EXISTS `scores`;
CREATE TABLE `scores` (
  `SC_ID` char(16) NOT NULL COMMENT '成绩id',
  `P_ID` char(16) NOT NULL COMMENT '试卷id',
  `S_ID` char(16) NOT NULL COMMENT '学生id',
  `sc_score` float DEFAULT NULL COMMENT '成绩',
  `c_id` varchar(16) DEFAULT NULL COMMENT '课程id',
  `p_name` varchar(30) DEFAULT NULL COMMENT '试卷名称',
  `sc_Date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '成绩生成时间',
  `ifdone` char(2) DEFAULT '0' COMMENT '是否批改完 0未完成1完成',
  `s_name` varchar(10) DEFAULT NULL COMMENT '学生姓名',
  `cls_id` varchar(16) DEFAULT NULL COMMENT '班级id',
  `cls_name` varchar(16) DEFAULT NULL COMMENT '班级名称',
  PRIMARY KEY (`SC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of scores
-- ----------------------------
INSERT INTO `scores` VALUES ('42cc3c1321324277', '91b894363d854104', '13115011019', '10', '1', '大学物理考试', '2017-04-23 10:06:02', '1', '张文康', '2013002', '软件工程1班');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `S_ID` varchar(12) NOT NULL COMMENT '学生id',
  `S_Pass` varchar(20) NOT NULL COMMENT '学生密码',
  `S_Name` varchar(12) NOT NULL COMMENT '学生真实姓名',
  `S_Sex` char(2) NOT NULL COMMENT '性别',
  `CLS_ID` char(12) NOT NULL COMMENT '班级id',
  PRIMARY KEY (`S_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('13115011019', '123456', '张文康', '男', '2013002');

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `T_ID` varchar(12) NOT NULL COMMENT '教师id',
  `T_Pass` varchar(15) DEFAULT NULL COMMENT '教师密码',
  `T_Name` varchar(12) DEFAULT NULL COMMENT '教师真实姓名',
  `T_Job` varchar(30) DEFAULT NULL COMMENT '职位',
  PRIMARY KEY (`T_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('123123', '123123', '李老师', '博士');
