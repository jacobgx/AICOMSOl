/*
 * washing_machine_vibration.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:23 by COMSOL 6.3.0.290. */
public class washing_machine_vibration {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Machinery_and_Robotics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");
    model.component("comp1").physics().create("shell", "Shell", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/mbd", true);
    model.study("std1").feature("eig").setSolveFor("/physics/shell", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Mc", "0.25[kg]", "\u4e0d\u5e73\u6574\u8863\u7269\u7684\u8d28\u91cf");
    model.param().set("Mm", "2[kg]", "\u53d1\u52a8\u673a\u8d28\u91cf");
    model.param().set("Im", "0.003[kg*m^2]", "\u53d1\u52a8\u673a\u60ef\u6027\u77e9");
    model.param().set("ks", "3000[N/m]", "\u652f\u67b6\u7684\u5f39\u7c27\u5e38\u6570");
    model.param().set("cs", "100[N*s/m]", "\u652f\u67b6\u7684\u963b\u5c3c\u7cfb\u6570");
    model.param().set("kbr", "3[N*m/deg]", "\u5957\u7ba1\u7684\u5f39\u7c27\u5e38\u6570");
    model.param().set("cbr", "0.05[N*m*s/deg]", "\u5957\u7ba1\u7684\u963b\u5c3c\u7cfb\u6570");
    model.param().set("kt", "1000[N/m]", "\u7a33\u5b9a\u5f39\u7c27\u7684\u5f39\u7c27\u5e38\u6570");
    model.param().set("rho0", "5000[kg/m^3]", "\u90e8\u4ef6\u5bc6\u5ea6");
    model.param().set("omega", "(100*2*pi/60)[rad/s]", "\u6eda\u7b52\u7684\u89d2\u901f\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "washing_machine_vibration.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);
    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(2);
    model.component("comp1").view("view1").hideObjects("hide1").set("fin", 1, 2, 5, 6, 10, 12, 14);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u673a\u58f3");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(36);
    model.component("comp1").selection("sel1").set("groupcontang", true);

    model.component("comp1").physics("mbd").create("rd1", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd1").label("\u8863\u7269");
    model.component("comp1").physics("mbd").feature("rd1").selection().set(13);
    model.component("comp1").physics("mbd").feature("rd1").set("rho_mat", "userdef");
    model.component("comp1").physics("mbd").feature("rd1").create("mmi1", "MassInertia", -1);
    model.component("comp1").physics("mbd").feature("rd1").feature("mmi1").set("mt", "Mc");
    model.component("comp1").physics("mbd").feature("rd1").create("af1", "AppliedForce", -1);
    model.component("comp1").physics("mbd").feature("rd1").feature("af1")
         .set("Ft", new String[]{"0", "0", "-Mc*g_const"});
    model.component("comp1").physics("mbd").create("rd2", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd2").label("\u6eda\u7b52");
    model.component("comp1").physics("mbd").feature("rd2").selection().set(8);
    model.component("comp1").physics("mbd").feature("rd2").set("InitialValueType", "locallyDefined");
    model.component("comp1").physics("mbd").feature("rd2").set("ConsistentInitialization", "ForceInitialValues");
    model.component("comp1").physics("mbd").feature("rd2").set("TranslationFirstAxis", true);
    model.component("comp1").physics("mbd").feature("rd2").set("TranslationSecondAxis", true);
    model.component("comp1").physics("mbd").feature("rd2").set("TranslationThirdAxis", true);
    model.component("comp1").physics("mbd").feature("rd2").set("TotalRotation", true);
    model.component("comp1").physics("mbd").feature("rd2").feature("init1")
         .set("CenterOfRotationType", "CentroidOfSelectedEntities");
    model.component("comp1").physics("mbd").feature("rd2").feature("init1").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("rd2").feature("init1")
         .set("omega", new String[]{"0", "omega", "0"});
    model.component("comp1").physics("mbd").feature("rd2").feature("init1").feature("crp1").selection()
         .set(141, 151);
    model.component("comp1").physics("mbd").create("rd3", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd3").label("\u5916\u6876");
    model.component("comp1").physics("mbd").feature("rd3").selection().set(5, 15, 16);
    model.component("comp1").physics("mbd").create("rd4", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd4").label("\u53d1\u52a8\u673a");
    model.component("comp1").physics("mbd").feature("rd4").selection().set(14);
    model.component("comp1").physics("mbd").feature("rd4").set("rho_mat", "userdef");
    model.component("comp1").physics("mbd").feature("rd4").create("mmi1", "MassInertia", -1);
    model.component("comp1").physics("mbd").feature("rd4").feature("mmi1").set("mt", "Mm");
    model.component("comp1").physics("mbd").feature("rd4").feature("mmi1")
         .set("mi", new String[]{"Im", "0", "0", "0", "Im", "0", "0", "0", "Im"});
    model.component("comp1").physics("mbd").create("rd5", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd5").label("\u6d3b\u585e 1");
    model.component("comp1").physics("mbd").feature("rd5").selection().set(11);
    model.component("comp1").physics("mbd").feature().duplicate("rd6", "rd5");
    model.component("comp1").physics("mbd").feature("rd6").label("\u6d3b\u585e 2");
    model.component("comp1").physics("mbd").feature("rd6").selection().set(17);
    model.component("comp1").physics("mbd").feature().duplicate("rd7", "rd6");
    model.component("comp1").physics("mbd").feature("rd7").label("\u6d3b\u585e 3");
    model.component("comp1").physics("mbd").feature("rd7").selection().set(18);
    model.component("comp1").physics("mbd").feature().duplicate("rd8", "rd7");
    model.component("comp1").physics("mbd").feature("rd8").label("\u6d3b\u585e 4");
    model.component("comp1").physics("mbd").feature("rd8").selection().set(12);
    model.component("comp1").physics("mbd").feature().duplicate("rd9", "rd8");
    model.component("comp1").physics("mbd").feature("rd9").label("\u8f8a\u7b52 1");
    model.component("comp1").physics("mbd").feature("rd9").selection().set(9);
    model.component("comp1").physics("mbd").feature().duplicate("rd10", "rd9");
    model.component("comp1").physics("mbd").feature("rd10").label("\u8f8a\u7b52 2");
    model.component("comp1").physics("mbd").feature("rd10").selection().set(19);
    model.component("comp1").physics("mbd").feature().duplicate("rd11", "rd10");
    model.component("comp1").physics("mbd").feature("rd11").label("\u8f8a\u7b52 3");
    model.component("comp1").physics("mbd").feature("rd11").selection().set(20);
    model.component("comp1").physics("mbd").feature().duplicate("rd12", "rd11");
    model.component("comp1").physics("mbd").feature("rd12").label("\u8f8a\u7b52 4");
    model.component("comp1").physics("mbd").feature("rd12").selection().set(10);
    model.component("comp1").physics("mbd").feature().duplicate("rd13", "rd12");
    model.component("comp1").physics("mbd").feature("rd13").label("\u652f\u5ea7 1");
    model.component("comp1").physics("mbd").feature("rd13").selection().set(6);
    model.component("comp1").physics("mbd").feature().duplicate("rd14", "rd13");
    model.component("comp1").physics("mbd").feature("rd14").label("\u652f\u5ea7 2");
    model.component("comp1").physics("mbd").feature("rd14").selection().set(21);
    model.component("comp1").physics("mbd").feature().duplicate("rd15", "rd14");
    model.component("comp1").physics("mbd").feature("rd15").label("\u652f\u5ea7 3");
    model.component("comp1").physics("mbd").feature("rd15").selection().set(22);
    model.component("comp1").physics("mbd").feature().duplicate("rd16", "rd15");
    model.component("comp1").physics("mbd").feature("rd16").label("\u652f\u5ea7 4");
    model.component("comp1").physics("mbd").feature("rd16").selection().set(7);
    model.component("comp1").physics("mbd").feature().duplicate("rd17", "rd16");
    model.component("comp1").physics("mbd").feature("rd17").label("\u5e95\u5ea7");
    model.component("comp1").physics("mbd").feature("rd17").selection().set(1, 2, 3, 4);
    model.component("comp1").physics("mbd").feature("rd17").create("fix1", "FixedConstraint", -1);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho0"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat2").label("Aluminum");
    model.component("comp1").material("mat2").set("family", "aluminum");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material("mat2").selection().geom("geom1", 2);
    model.component("comp1").material("mat2").selection().named("sel1");

    model.component("comp1").physics("shell").label("\u58f3[\u673a\u58f3]");
    model.component("comp1").physics("shell").selection().named("sel1");
    model.component("comp1").physics("shell").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("shell").feature("to1").set("d", "0.001[m]");
    model.component("comp1").physics("shell").feature("emm1").set("geometricNonlinearity", "linear");
    model.component("comp1").physics("shell").create("att1", "Attachment", 1);
    model.component("comp1").physics("shell").feature("att1").label("\u524d\u5f39\u7c27");
    model.component("comp1").physics("shell").feature("att1").selection().set(45, 46, 49, 50);
    model.component("comp1").physics("shell").feature().duplicate("att2", "att1");
    model.component("comp1").physics("shell").feature("att2").label("\u540e\u5f39\u7c27");
    model.component("comp1").physics("shell").feature("att2").selection().set(47, 48, 51, 52);
    model.component("comp1").physics("shell").feature().duplicate("att3", "att2");
    model.component("comp1").physics("shell").feature("att3").label("\u652f\u5ea7 1");
    model.component("comp1").physics("shell").feature("att3").selection().set(37, 38, 41, 42);
    model.component("comp1").physics("shell").feature().duplicate("att4", "att3");
    model.component("comp1").physics("shell").feature("att4").label("\u652f\u5ea7 2");
    model.component("comp1").physics("shell").feature("att4").selection().set(53, 54, 57, 58);
    model.component("comp1").physics("shell").feature().duplicate("att5", "att4");
    model.component("comp1").physics("shell").feature("att5").label("\u652f\u5ea7 3");
    model.component("comp1").physics("shell").feature("att5").selection().set(55, 56, 59, 60);
    model.component("comp1").physics("shell").feature().duplicate("att6", "att5");
    model.component("comp1").physics("shell").feature("att6").label("\u652f\u5ea7 4");
    model.component("comp1").physics("shell").feature("att6").selection().set(39, 40, 43, 44);
    model.component("comp1").physics("shell").feature().duplicate("att7", "att6");
    model.component("comp1").physics("shell").feature("att7").label("\u5e95\u5ea7 1");
    model.component("comp1").physics("shell").feature("att7").selection().set(29, 30, 33, 34);
    model.component("comp1").physics("shell").feature().duplicate("att8", "att7");
    model.component("comp1").physics("shell").feature("att8").label("\u5e95\u5ea7 2");
    model.component("comp1").physics("shell").feature("att8").selection().set(61, 62, 65, 66);
    model.component("comp1").physics("shell").feature().duplicate("att9", "att8");
    model.component("comp1").physics("shell").feature("att9").label("\u5e95\u5ea7 3");
    model.component("comp1").physics("shell").feature("att9").selection().set(63, 64, 67, 68);
    model.component("comp1").physics("shell").feature().duplicate("att10", "att9");
    model.component("comp1").physics("shell").feature("att10").label("\u5e95\u5ea7 4");
    model.component("comp1").physics("shell").feature("att10").selection().set(31, 32, 35, 36);
    model.component("comp1").physics("mbd").create("spd1", "SpringDamper", -1);
    model.component("comp1").physics("mbd").feature("spd1")
         .label("\u673a\u58f3-\u5916\u6876\uff08\u524d\u9762\uff09");
    model.component("comp1").physics("mbd").feature("spd1").set("Source", "root.comp1.shell.att1");
    model.component("comp1").physics("mbd").feature("spd1").set("k", "kt");
    model.component("comp1").physics("mbd").feature("spd1").feature("dp1").selection().set(246, 248);
    model.component("comp1").physics("mbd").feature().duplicate("spd2", "spd1");
    model.component("comp1").physics("mbd").feature("spd2")
         .label("\u673a\u58f3-\u5916\u6876\uff08\u80cc\u9762\uff09");
    model.component("comp1").physics("mbd").feature("spd2").set("Source", "root.comp1.shell.att2");
    model.component("comp1").physics("mbd").feature("spd2").feature("dp1").selection().set(258, 260);
    model.component("comp1").physics("mbd").create("hgj1", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj1").label("\u5916\u6876-\u6eda\u7b52");
    model.component("comp1").physics("mbd").feature("hgj1").set("Source", "rd3");
    model.component("comp1").physics("mbd").feature("hgj1").set("Destination", "rd2");
    model.component("comp1").physics("mbd").feature("hgj1").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("hgj1").set("e", new int[]{0, 1, 0});
    model.component("comp1").physics("mbd").feature("hgj1").feature("cjp1").selection().set(141, 151);
    model.component("comp1").physics("mbd").feature("hgj1").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("hgj1").feature("pm1")
         .set("PrescribedMotionThroughRotational", "AngularVelocity");
    model.component("comp1").physics("mbd").feature("hgj1").feature("pm1").set("omegap", "omega");
    model.component("comp1").physics("mbd").create("fxj1", "FixedJoint", -1);
    model.component("comp1").physics("mbd").feature("fxj1").label("\u7535\u673a-\u6876");
    model.component("comp1").physics("mbd").feature("fxj1").set("Source", "rd4");
    model.component("comp1").physics("mbd").feature("fxj1").set("Destination", "rd3");
    model.component("comp1").physics("mbd").feature("fxj1").feature("cjb1").selection().set(168, 171);
    model.component("comp1").physics("mbd").feature().duplicate("fxj2", "fxj1");
    model.component("comp1").physics("mbd").feature("fxj2").label("\u6eda\u7b52-\u8863\u7269");
    model.component("comp1").physics("mbd").feature("fxj2").set("Source", "rd2");
    model.component("comp1").physics("mbd").feature("fxj2").set("Destination", "rd1");
    model.component("comp1").physics("mbd").feature("fxj2").feature("cjb1").selection().set(157);
    model.component("comp1").physics("mbd").create("prj1", "PrismaticJoint", -1);
    model.component("comp1").physics("mbd").feature("prj1").label("\u8f8a\u7b52 1 - \u6d3b\u585e 1");
    model.component("comp1").physics("mbd").feature("prj1").set("Source", "rd9");
    model.component("comp1").physics("mbd").feature("prj1").set("Destination", "rd5");
    model.component("comp1").physics("mbd").feature("prj1").set("AxisOfJointType", "SelectEdge");
    model.component("comp1").physics("mbd").feature("prj1").feature("cjb1").selection().set(144);
    model.component("comp1").physics("mbd").feature("prj1").feature("ja1").selection().set(228);
    model.component("comp1").physics("mbd").feature("prj1").create("sd1", "SpringAndDamper", -1);
    model.component("comp1").physics("mbd").feature("prj1").feature("sd1").set("k_u", "ks");
    model.component("comp1").physics("mbd").feature("prj1").feature("sd1").set("c_u", "cs");
    model.component("comp1").physics("mbd").feature().duplicate("prj2", "prj1");
    model.component("comp1").physics("mbd").feature("prj2").label("\u8f8a\u7b52 2 - \u6d3b\u585e 2");
    model.component("comp1").physics("mbd").feature("prj2").set("Source", "rd10");
    model.component("comp1").physics("mbd").feature("prj2").set("Destination", "rd6");
    model.component("comp1").physics("mbd").feature("prj2").feature("cjb1").selection().set(195);
    model.component("comp1").physics("mbd").feature("prj2").feature("ja1").selection().set(444);
    model.component("comp1").physics("mbd").feature().duplicate("prj3", "prj2");
    model.component("comp1").physics("mbd").feature("prj3").label("\u8f8a\u7b52 3 - \u6d3b\u585e 3");
    model.component("comp1").physics("mbd").feature("prj3").set("Source", "rd11");
    model.component("comp1").physics("mbd").feature("prj3").set("Destination", "rd7");
    model.component("comp1").physics("mbd").feature("prj3").feature("cjb1").selection().set(201);
    model.component("comp1").physics("mbd").feature("prj3").feature("ja1").selection().set(484);
    model.component("comp1").physics("mbd").feature().duplicate("prj4", "prj3");
    model.component("comp1").physics("mbd").feature("prj4").label("\u8f8a\u7b52 4 - \u6d3b\u585e 4");
    model.component("comp1").physics("mbd").feature("prj4").set("Source", "rd12");
    model.component("comp1").physics("mbd").feature("prj4").set("Destination", "rd8");
    model.component("comp1").physics("mbd").feature("prj4").feature("cjb1").selection().set(150);
    model.component("comp1").physics("mbd").feature("prj4").feature("ja1").selection().set(268);
    model.component("comp1").physics("mbd").create("fxj3", "FixedJoint", -1);
    model.component("comp1").physics("mbd").feature("fxj3").label("\u5916\u6876-\u6d3b\u585e 1");
    model.component("comp1").physics("mbd").feature("fxj3").set("Source", "rd3");
    model.component("comp1").physics("mbd").feature("fxj3").set("Destination", "rd5");
    model.component("comp1").physics("mbd").feature("fxj3").set("AxisOfJointType", "SelectEdge");
    model.component("comp1").physics("mbd").feature("fxj3").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("fxj3").feature("cjb1").selection().set(149);
    model.component("comp1").physics("mbd").feature("fxj3").feature("ja1").selection().set(228);
    model.component("comp1").physics("mbd").feature("fxj3").feature("je1").set("ul1", false);
    model.component("comp1").physics("mbd").feature("fxj3").feature("je1").set("ul2", false);
    model.component("comp1").physics("mbd").feature("fxj3").feature("je1").set("ul3", false);
    model.component("comp1").physics("mbd").feature("fxj3").feature("je1")
         .set("k_th", new String[]{"kbr", "0", "0", "0", "kbr", "0", "0", "0", "kbr"});
    model.component("comp1").physics("mbd").feature("fxj3").feature("je1")
         .set("c_th", new String[]{"cbr", "0", "0", "0", "cbr", "0", "0", "0", "cbr"});
    model.component("comp1").physics("mbd").feature().duplicate("fxj4", "fxj3");
    model.component("comp1").physics("mbd").feature("fxj4").label("\u5916\u6876-\u6d3b\u585e 2");
    model.component("comp1").physics("mbd").feature("fxj4").set("Destination", "rd6");
    model.component("comp1").physics("mbd").feature("fxj4").feature("cjb1").selection().set(190);
    model.component("comp1").physics("mbd").feature("fxj4").feature("ja1").selection().set(444);
    model.component("comp1").physics("mbd").feature().duplicate("fxj5", "fxj4");
    model.component("comp1").physics("mbd").feature("fxj5").label("\u5916\u6876-\u6d3b\u585e 3");
    model.component("comp1").physics("mbd").feature("fxj5").set("Destination", "rd7");
    model.component("comp1").physics("mbd").feature("fxj5").feature("cjb1").selection().set(196);
    model.component("comp1").physics("mbd").feature("fxj5").feature("ja1").selection().set(484);
    model.component("comp1").physics("mbd").feature().duplicate("fxj6", "fxj5");
    model.component("comp1").physics("mbd").feature("fxj6").label("\u5916\u6876-\u6d3b\u585e 4");
    model.component("comp1").physics("mbd").feature("fxj6").set("Destination", "rd8");
    model.component("comp1").physics("mbd").feature("fxj6").feature("cjb1").selection().set(155);
    model.component("comp1").physics("mbd").feature("fxj6").feature("ja1").selection().set(268);
    model.component("comp1").physics("mbd").feature().duplicate("fxj7", "fxj6");
    model.component("comp1").physics("mbd").feature("fxj7").label("\u8f8a\u7b52 1 - \u652f\u5ea7 1");
    model.component("comp1").physics("mbd").feature("fxj7").set("Source", "rd9");
    model.component("comp1").physics("mbd").feature("fxj7").set("Destination", "rd13");
    model.component("comp1").physics("mbd").feature("fxj7").feature("cjb1").selection().set(74);
    model.component("comp1").physics("mbd").feature("fxj7").feature("ja1").selection().set(228);
    model.component("comp1").physics("mbd").feature().duplicate("fxj8", "fxj7");
    model.component("comp1").physics("mbd").feature("fxj8").label("\u8f8a\u7b52 2 - \u652f\u5ea7 2");
    model.component("comp1").physics("mbd").feature("fxj8").set("Source", "rd10");
    model.component("comp1").physics("mbd").feature("fxj8").set("Destination", "rd14");
    model.component("comp1").physics("mbd").feature("fxj8").feature("cjb1").selection().set(247);
    model.component("comp1").physics("mbd").feature("fxj8").feature("ja1").selection().set(444);
    model.component("comp1").physics("mbd").feature().duplicate("fxj9", "fxj8");
    model.component("comp1").physics("mbd").feature("fxj9").label("\u8f8a\u7b52 3 - \u652f\u5ea7 3");
    model.component("comp1").physics("mbd").feature("fxj9").set("Source", "rd11");
    model.component("comp1").physics("mbd").feature("fxj9").set("Destination", "rd15");
    model.component("comp1").physics("mbd").feature("fxj9").feature("cjb1").selection().set(257);
    model.component("comp1").physics("mbd").feature("fxj9").feature("ja1").selection().set(484);
    model.component("comp1").physics("mbd").feature().duplicate("fxj10", "fxj9");
    model.component("comp1").physics("mbd").feature("fxj10").label("\u8f8a\u7b52 4 - \u652f\u5ea7 4");
    model.component("comp1").physics("mbd").feature("fxj10").set("Source", "rd12");
    model.component("comp1").physics("mbd").feature("fxj10").set("Destination", "rd16");
    model.component("comp1").physics("mbd").feature("fxj10").feature("cjb1").selection().set(84);
    model.component("comp1").physics("mbd").feature("fxj10").feature("ja1").selection().set(268);
    model.component("comp1").physics("mbd").feature("prj2").set("ReverseDirection", true);
    model.component("comp1").physics("mbd").feature("prj3").set("ReverseDirection", true);
    model.component("comp1").physics("mbd").feature("fxj4").set("ReverseDirection", true);
    model.component("comp1").physics("mbd").feature("fxj5").set("ReverseDirection", true);
    model.component("comp1").physics("mbd").feature("fxj8").set("ReverseDirection", true);
    model.component("comp1").physics("mbd").feature("fxj9").set("ReverseDirection", true);
    model.component("comp1").physics("mbd").feature().duplicate("fxj11", "fxj10");
    model.component("comp1").physics("mbd").feature("fxj11").label("\u652f\u5ea7 1 - \u673a\u58f3");
    model.component("comp1").physics("mbd").feature("fxj11").set("Source", "rd13");
    model.component("comp1").physics("mbd").feature("fxj11").set("Destination", "root.comp1.shell.att3");
    model.component("comp1").physics("mbd").feature("fxj11").set("AxisOfJointType", "SpecifyDirection");
    model.component("comp1").physics("mbd").feature("fxj11").set("JointElasticity", "RigidJoint");
    model.component("comp1").physics("mbd").feature("fxj11").set("CenterOfJointType", "CentroidOfDestination");
    model.component("comp1").physics("mbd").feature().duplicate("fxj12", "fxj11");
    model.component("comp1").physics("mbd").feature("fxj12").label("\u652f\u5ea7 2 - \u673a\u58f3");
    model.component("comp1").physics("mbd").feature("fxj12").set("Source", "rd14");
    model.component("comp1").physics("mbd").feature("fxj12").set("Destination", "root.comp1.shell.att4");
    model.component("comp1").physics("mbd").feature().duplicate("fxj13", "fxj12");
    model.component("comp1").physics("mbd").feature("fxj13").label("\u652f\u5ea7 3 - \u673a\u58f3");
    model.component("comp1").physics("mbd").feature("fxj13").set("Source", "rd15");
    model.component("comp1").physics("mbd").feature("fxj13").set("Destination", "root.comp1.shell.att5");
    model.component("comp1").physics("mbd").feature().duplicate("fxj14", "fxj13");
    model.component("comp1").physics("mbd").feature("fxj14").label("\u652f\u5ea7 4 - \u673a\u58f3");
    model.component("comp1").physics("mbd").feature("fxj14").set("Source", "rd16");
    model.component("comp1").physics("mbd").feature("fxj14").set("Destination", "root.comp1.shell.att6");
    model.component("comp1").physics("mbd").feature().duplicate("fxj15", "fxj14");
    model.component("comp1").physics("mbd").feature("fxj15").label("\u673a\u58f3 - \u5e95\u5ea7 1");
    model.component("comp1").physics("mbd").feature("fxj15").set("Source", "root.comp1.shell.att7");
    model.component("comp1").physics("mbd").feature("fxj15").set("Destination", "rd17");
    model.component("comp1").physics("mbd").feature().duplicate("fxj16", "fxj15");
    model.component("comp1").physics("mbd").feature("fxj16").label("\u673a\u58f3 - \u5e95\u5ea7 2");
    model.component("comp1").physics("mbd").feature("fxj16").set("Source", "root.comp1.shell.att8");
    model.component("comp1").physics("mbd").feature().duplicate("fxj17", "fxj16");
    model.component("comp1").physics("mbd").feature("fxj17").label("\u673a\u58f3 - \u5e95\u5ea7 3");
    model.component("comp1").physics("mbd").feature("fxj17").set("Source", "root.comp1.shell.att9");
    model.component("comp1").physics("mbd").feature().duplicate("fxj18", "fxj17");
    model.component("comp1").physics("mbd").feature("fxj18").label("\u673a\u58f3 - \u5e95\u5ea7 4");
    model.component("comp1").physics("mbd").feature("fxj18").set("Source", "root.comp1.shell.att10");

    model.nodeGroup().create("grp1", "Physics", "mbd");
    model.nodeGroup("grp1").placeAfter("init1");
    model.nodeGroup("grp1").add("rd1");
    model.nodeGroup("grp1").add("rd2");
    model.nodeGroup("grp1").add("rd3");
    model.nodeGroup("grp1").add("rd4");
    model.nodeGroup("grp1").add("rd5");
    model.nodeGroup("grp1").add("rd6");
    model.nodeGroup("grp1").add("rd7");
    model.nodeGroup("grp1").add("rd8");
    model.nodeGroup("grp1").add("rd9");
    model.nodeGroup("grp1").add("rd10");
    model.nodeGroup("grp1").add("rd11");
    model.nodeGroup("grp1").add("rd12");
    model.nodeGroup("grp1").add("rd13");
    model.nodeGroup("grp1").add("rd14");
    model.nodeGroup("grp1").add("rd15");
    model.nodeGroup("grp1").add("rd16");
    model.nodeGroup("grp1").add("rd17");
    model.nodeGroup("grp1").label("\u521a\u6027\u6750\u6599");
    model.nodeGroup().create("grp2", "Physics", "mbd");
    model.nodeGroup("grp2").placeAfter("hgj1");
    model.nodeGroup("grp2").add("fxj1");
    model.nodeGroup("grp2").add("fxj2");
    model.nodeGroup("grp2").add("fxj3");
    model.nodeGroup("grp2").add("fxj4");
    model.nodeGroup("grp2").add("fxj5");
    model.nodeGroup("grp2").add("fxj6");
    model.nodeGroup("grp2").add("fxj7");
    model.nodeGroup("grp2").add("fxj8");
    model.nodeGroup("grp2").add("fxj9");
    model.nodeGroup("grp2").add("fxj10");
    model.nodeGroup("grp2").add("fxj11");
    model.nodeGroup("grp2").add("fxj12");
    model.nodeGroup("grp2").add("fxj13");
    model.nodeGroup("grp2").add("fxj14");
    model.nodeGroup("grp2").add("fxj15");
    model.nodeGroup("grp2").add("fxj16");
    model.nodeGroup("grp2").add("fxj17");
    model.nodeGroup("grp2").add("fxj18");
    model.nodeGroup("grp2").label("\u56fa\u5b9a\u5173\u8282");
    model.nodeGroup().create("grp3", "Physics", "mbd");
    model.nodeGroup("grp3").placeAfter("hgj1");
    model.nodeGroup("grp3").add("prj1");
    model.nodeGroup("grp3").add("prj2");
    model.nodeGroup("grp3").add("prj3");
    model.nodeGroup("grp3").add("prj4");
    model.nodeGroup("grp3").label("\u68f1\u67f1\u5173\u8282");
    model.nodeGroup().create("grp4", "Physics", "mbd");
    model.nodeGroup("grp4").placeAfter("init1");
    model.nodeGroup("grp4").add("spd1");
    model.nodeGroup("grp4").add("spd2");
    model.nodeGroup("grp4").label("\u5f39\u7c27\u963b\u5c3c\u5668");

    return model;
  }

  public static Model run2(Model model) {
    model.nodeGroup().create("grp5", "Physics", "shell");
    model.nodeGroup("grp5").placeAfter("dcont1");
    model.nodeGroup("grp5").add("att1");
    model.nodeGroup("grp5").add("att2");
    model.nodeGroup("grp5").add("att3");
    model.nodeGroup("grp5").add("att4");
    model.nodeGroup("grp5").add("att5");
    model.nodeGroup("grp5").add("att6");
    model.nodeGroup("grp5").add("att7");
    model.nodeGroup("grp5").add("att8");
    model.nodeGroup("grp5").add("att9");
    model.nodeGroup("grp5").add("att10");
    model.nodeGroup("grp5").label("\u8fde\u63a5\u4ef6");

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("eig").set("shift", "2");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u632f\u578b");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").set("expr", "shell.disp");
    model.result("pg1").feature("surf2").set("titletype", "none");
    model.result("pg1").feature("surf2").set("colortable", "GrayScale");
    model.result("pg1").feature("surf2").set("colortabletrans", "reverse");
    model.result("pg1").feature("surf2").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("def1").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{3});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{4});
    model.result("pg1").run();

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("uin_tub", "sqrt(mbd.rd3.u^2+mbd.rd3.w^2)");
    model.component("comp1").variable("var1")
         .descr("uin_tub", "\u5916\u6876\u4f4d\u79fb\u5927\u5c0f\uff08\u9762\u5185\uff09");
    model.component("comp1").variable("var1").set("uout_tub", "abs(mbd.rd3.v)");
    model.component("comp1").variable("var1")
         .descr("uout_tub", "\u5916\u6876\u4f4d\u79fb\u5927\u5c0f\uff08\u9762\u5916\uff09");
    model.component("comp1").variable("var1").set("th_drum", "mbd.hgj1.th");
    model.component("comp1").variable("var1").descr("th_drum", "\u6eda\u7b52\u8f6c\u52a8");
    model.component("comp1").variable("var1").set("n_cycle", "th_drum/360[deg]");
    model.component("comp1").variable("var1").descr("n_cycle", "\u5faa\u73af\u6b21\u6570");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/mbd", true);
    model.study("std2").feature("time").setSolveFor("/physics/shell", true);
    model.study("std2").feature("time").set("tlist", "range(0,0.01,2)");
    model.study("std2").setGenPlots(false);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").set("atolglobalvaluemethod", "manual");
    model.sol("sol2").feature("t1").set("tstepsbdf", "intermediate");
    model.sol("sol2").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol2").feature("t1").feature("fc1").set("maxiter", 15);
    model.sol("sol2").feature("t1").feature("aDef").set("nullfun", "flnullorth");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u4f4d\u79fb");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 101, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg2").run();
    model.result("pg2").feature("surf2").feature("def1").set("scaleactive", true);
    model.result("pg2").feature("surf2").feature("def1").set("scale", 1);
    model.result("pg2").run();
    model.result().create("pg3", "PolarGroup");
    model.result("pg3").run();
    model.result("pg3").label("\u5916\u6876\u4f4d\u79fb\u5927\u5c0f\uff08\u9762\u5185\uff09");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"uin_tub"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u5916\u6876\u4f4d\u79fb\u5927\u5c0f\uff08\u9762\u5185\uff09"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"mm"});
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "th_drum");
    model.result("pg3").feature("glob1").set("xdatadescr", "\u6eda\u7b52\u8f6c\u52a8");
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").feature("glob1").set("legend", false);
    model.result("pg3").feature("glob1").create("col1", "Color");
    model.result("pg3").run();
    model.result("pg3").feature("glob1").feature("col1").set("expr", "t");
    model.result("pg3").feature("glob1").feature("col1").set("descr", "\u65f6\u95f4");
    model.result("pg3").feature("glob1").feature("col1").set("colortabletrans", "reverse");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u5916\u6876\u4f4d\u79fb\u5927\u5c0f\uff08\u9762\u5916\uff09");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").set("expr", new String[]{"uout_tub"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u5916\u6876\u4f4d\u79fb\u5927\u5c0f\uff08\u9762\u5916\uff09"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"mm"});
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u5916\u6876\u8f6c\u52a8");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u5faa\u73af\u6b21\u6570");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u5916\u6876\u8f6c\u52a8 (deg)");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("expr", new String[]{"mbd.rd3.thx"});
    model.result("pg5").feature("glob1").set("descr", new String[]{"\u521a\u4f53\u8f6c\u52a8\uff0cx \u5206\u91cf"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"rad"});
    model.result("pg5").feature("glob1").set("expr", new String[]{"mbd.rd3.thx", "mbd.rd3.thy"});
    model.result("pg5").feature("glob1")
         .set("descr", new String[]{"\u521a\u4f53\u8f6c\u52a8\uff0cx \u5206\u91cf", "\u521a\u4f53\u8f6c\u52a8\uff0cy \u5206\u91cf"});
    model.result("pg5").feature("glob1").set("expr", new String[]{"mbd.rd3.thx", "mbd.rd3.thy", "mbd.rd3.thz"});
    model.result("pg5").feature("glob1")
         .set("descr", new String[]{"\u521a\u4f53\u8f6c\u52a8\uff0cx \u5206\u91cf", "\u521a\u4f53\u8f6c\u52a8\uff0cy \u5206\u91cf", "\u521a\u4f53\u8f6c\u52a8\uff0cz \u5206\u91cf"});
    model.result("pg5").feature("glob1").setIndex("unit", "deg", 0);
    model.result("pg5").feature("glob1").setIndex("unit", "deg", 1);
    model.result("pg5").feature("glob1").setIndex("unit", "deg", 2);
    model.result("pg5").feature("glob1").set("xdataexpr", "n_cycle");
    model.result("pg5").feature("glob1").set("xdatadescr", "\u5faa\u73af\u6b21\u6570");
    model.result("pg5").feature("glob1").set("linewidth", 2);
    model.result("pg5").feature("glob1").set("linemarker", "cycle");
    model.result("pg5").feature("glob1").set("markerpos", "interp");
    model.result("pg5").feature("glob1").set("markers", 20);
    model.result("pg5").feature("glob1").set("legendmethod", "manual");
    model.result("pg5").feature("glob1").setIndex("legends", "x \u5206\u91cf", 0);
    model.result("pg5").feature("glob1").setIndex("legends", "y \u5206\u91cf", 1);
    model.result("pg5").feature("glob1").setIndex("legends", "z \u5206\u91cf", 2);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u7a33\u5b9a\u5f39\u7c27\u4f38\u957f\u91cf");
    model.result("pg6").set("ylabelactive", false);
    model.result("pg6").set("legendpos", "lowerleft");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").set("expr", new String[]{"mbd.spd1.dl"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"\u5f39\u7c27\u4f38\u957f\u91cf"});
    model.result("pg6").feature("glob1").set("unit", new String[]{"mm"});
    model.result("pg6").feature("glob1").set("expr", new String[]{"mbd.spd1.dl", "mbd.spd2.dl"});
    model.result("pg6").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u4f38\u957f\u91cf", "\u5f39\u7c27\u4f38\u957f\u91cf"});
    model.result("pg6").feature("glob1").setIndex("legends", "\u524d\u5f39\u7c27", 0);
    model.result("pg6").feature("glob1").setIndex("legends", "\u540e\u5f39\u7c27", 1);
    model.result("pg6").feature("glob1").setIndex("legends", "", 2);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u652f\u67f1\u4f4d\u79fb");
    model.result("pg7").set("legendpos", "upperright");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").set("expr", new String[]{"mbd.prj1.u"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"\u76f8\u5bf9\u4f4d\u79fb"});
    model.result("pg7").feature("glob1").set("unit", new String[]{"mm"});
    model.result("pg7").feature("glob1").set("expr", new String[]{"mbd.prj1.u", "mbd.prj2.u"});
    model.result("pg7").feature("glob1")
         .set("descr", new String[]{"\u76f8\u5bf9\u4f4d\u79fb", "\u76f8\u5bf9\u4f4d\u79fb"});
    model.result("pg7").feature("glob1").set("expr", new String[]{"mbd.prj1.u", "mbd.prj2.u", "mbd.prj3.u"});
    model.result("pg7").feature("glob1")
         .set("descr", new String[]{"\u76f8\u5bf9\u4f4d\u79fb", "\u76f8\u5bf9\u4f4d\u79fb", "\u76f8\u5bf9\u4f4d\u79fb"});
    model.result("pg7").feature("glob1")
         .set("expr", new String[]{"mbd.prj1.u", "mbd.prj2.u", "mbd.prj3.u", "mbd.prj4.u"});
    model.result("pg7").feature("glob1")
         .set("descr", new String[]{"\u76f8\u5bf9\u4f4d\u79fb", "\u76f8\u5bf9\u4f4d\u79fb", "\u76f8\u5bf9\u4f4d\u79fb", "\u76f8\u5bf9\u4f4d\u79fb"});
    model.result("pg7").feature("glob1").setIndex("legends", "\u652f\u67f1 1", 0);
    model.result("pg7").feature("glob1").setIndex("legends", "\u652f\u67f1 2", 1);
    model.result("pg7").feature("glob1").setIndex("legends", "\u652f\u67f1 3", 2);
    model.result("pg7").feature("glob1").setIndex("legends", "\u652f\u67f1 4", 3);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u80fd\u8017\u901f\u7387");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").setIndex("expr", "mbd.prj1.Qdamper", 0);
    model.result("pg8").feature("glob1").setIndex("expr", "mbd.prj2.Qdamper", 1);
    model.result("pg8").feature("glob1").setIndex("expr", "mbd.prj3.Qdamper", 2);
    model.result("pg8").feature("glob1").setIndex("expr", "mbd.prj4.Qdamper", 3);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("\u58f3\u53d8\u5f62\uff08\u652f\u5ea7\uff09");
    model.result("pg9").set("titletype", "label");
    model.result("pg9").run();
    model.result("pg9").feature("glob1").setIndex("expr", "shell.att3.w", 0);
    model.result("pg9").feature("glob1").setIndex("expr", "shell.att4.w", 1);
    model.result("pg9").feature("glob1").setIndex("expr", "shell.att5.w", 2);
    model.result("pg9").feature("glob1").setIndex("expr", "shell.att6.w", 3);
    model.result("pg9").feature("glob1").setIndex("legends", "\u652f\u5ea7 1", 0);
    model.result("pg9").feature("glob1").setIndex("legends", "\u652f\u5ea7 2", 1);
    model.result("pg9").feature("glob1").setIndex("legends", "\u652f\u5ea7 3", 2);
    model.result("pg9").feature("glob1").setIndex("legends", "\u652f\u5ea7 4", 3);
    model.result("pg9").run();
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("data", "dset2");
    model.result().dataset("cpt1").set("pointx", 600);
    model.result().dataset("cpt1").set("pointy", 250);
    model.result().dataset("cpt1").set("pointz", 450);
    model.result().dataset("cpt1").set("snapping", "boundary");
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u58f3\u53d8\u5f62\uff08\u4fa7\u58c1\uff09");
    model.result("pg10").set("data", "cpt1");
    model.result("pg10").set("titletype", "label");
    model.result("pg10").set("xlabelactive", true);
    model.result("pg10").set("xlabel", "\u5faa\u73af\u6b21\u6570");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "\u4f4d\u79fb (mm)");
    model.result("pg10").set("legendpos", "lowerleft");
    model.result("pg10").create("ptgr1", "PointGraph");
    model.result("pg10").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg10").feature("ptgr1").set("linewidth", "preference");
    model.result("pg10").feature("ptgr1").set("expr", "u2");
    model.result("pg10").feature("ptgr1").set("xdataexpr", "n_cycle");
    model.result("pg10").feature("ptgr1").set("xdatadescr", "\u5faa\u73af\u6b21\u6570");
    model.result("pg10").feature("ptgr1").set("linewidth", 2);
    model.result("pg10").feature("ptgr1").set("linemarker", "cycle");
    model.result("pg10").feature("ptgr1").set("markerpos", "interp");
    model.result("pg10").feature("ptgr1").set("markers", 20);
    model.result("pg10").feature("ptgr1").set("legend", true);
    model.result("pg10").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg10").feature("ptgr1").setIndex("legends", "x \u5206\u91cf", 0);
    model.result("pg10").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg10").run();
    model.result("pg10").feature("ptgr2").set("expr", "v2");
    model.result("pg10").feature("ptgr2").setIndex("legends", "y \u5206\u91cf", 0);
    model.result("pg10").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg10").run();
    model.result("pg10").feature("ptgr3").set("expr", "w2");
    model.result("pg10").feature("ptgr3").setIndex("legends", "z \u5206\u91cf", 0);
    model.result("pg10").run();
    model.result("pg1").run();

    model.title("\u6d17\u8863\u673a\u88c5\u914d\u632f\u52a8");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u6c34\u5e73\u8f74\u4fbf\u643a\u5f0f\u6d17\u8863\u673a\u7684\u7b80\u5316\u591a\u4f53\u52a8\u529b\u5b66\u6a21\u578b\u3002\u5176\u4e2d\u901a\u8fc7\u6267\u884c\u7279\u5f81\u9891\u7387\u5206\u6790\u6765\u8ba1\u7b97\u6574\u4e2a\u88c5\u914d\u7684\u56fa\u6709\u9891\u7387\u548c\u632f\u578b\uff0c\u5e76\u901a\u8fc7\u6267\u884c\u77ac\u6001\u5206\u6790\u5f97\u5230\u65cb\u8f6c\u5468\u671f\u4e2d\u673a\u58f3\u5185\u611f\u5e94\u7684\u632f\u52a8\uff0c\u5b83\u6a21\u62df\u4e3a\u4e00\u4e2a\u67d4\u6027\u58f3\u3002\u6b64\u5916\uff0c\u8fd8\u5206\u6790\u4e86\u6eda\u7b52\u7684\u5f84\u5411\u548c\u8f74\u5411\u4f4d\u79fb\u4ee5\u53ca\u7ed3\u6784\u7684\u80fd\u8017\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("washing_machine_vibration.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
