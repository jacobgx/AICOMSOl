/*
 * mechanism_submerged_in_fluid.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:24 by COMSOL 6.3.0.290. */
public class mechanism_submerged_in_fluid {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");
    model.component("comp1").physics("spf").field("velocity").field("u_fluid");
    model.component("comp1").physics("spf").field("velocity")
         .component(new String[]{"u_fluid", "v_fluid", "w_fluid"});
    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");
    model.component("comp1").physics("mbd").field("displacement").field("u_solid");
    model.component("comp1").physics("mbd").field("displacement")
         .component(new String[]{"u_solid", "v_solid", "w_solid"});
    model.component("comp1").physics("mbd").prop("ShapeProperty").set("order_displacement", "1");

    model.component("comp1").multiphysics().create("fsip1", "FluidStructureInteractionPair", 2);
    model.component("comp1").multiphysics("fsip1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("fsip1").set("Structure_physics", "mbd");
    model.component("comp1").multiphysics("fsip1").set("CouplingType", "FullyCoupled");

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").set("smoothingType", "yeoh");
    model.component("comp1").common("free1").selection().set();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/mbd", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/fsip1", true);

    model.param().set("th_max", "15[deg]");
    model.param().descr("th_max", "\u6700\u5927\u7fc5\u7247\u65cb\u8f6c");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "mechanism_submerged_in_fluid.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("imp1");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{25, 15, 5});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().duplicate("wp2", "wp1");
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "xz");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("pard1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard1").selection("domain").set("blk1", 1);
    model.component("comp1").geom("geom1").feature("pard1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").run("pard1");
    model.component("comp1").geom("geom1").feature().duplicate("pard2", "pard1");
    model.component("comp1").geom("geom1").feature("pard2").selection("domain").set("pard1", 1, 2);
    model.component("comp1").geom("geom1").feature("pard2").set("workplane", "wp2");
    model.component("comp1").geom("geom1").run("pard2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("pard2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("copy1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("frame", "material");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(3);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 2, 4);
    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", "0.05[s]");
    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("expr", "th_max*(sin(2*pi*1*t)*(t<0.25)*step1(t)+(t>=0.25))");
    model.component("comp1").func("an1").set("args", "t");
    model.component("comp1").func("an1").setIndex("argunit", "s", 0);
    model.component("comp1").func("an1").set("fununit", "rad");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("th", "an1(t)");
    model.component("comp1").variable("var1").descr("th", "\u7fc5\u7247\u65cb\u8f6c");
    model.component("comp1").variable("var1")
         .set("un_solid", "fsip1.u_solid*(nX)+fsip1.v_solid*(nY)+fsip1.w_solid*(nZ)");
    model.component("comp1").variable("var1")
         .descr("un_solid", "\u6cd5\u5411\u7f51\u683c\u4f4d\u79fb\uff0c\u6ed1\u52a8\u8fb9\u754c");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("\u6d41\u4f53\u8fb9\u754c\uff08\u7fc5\u7247\uff09");
    model.component("comp1").selection("sel1")
         .set(17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").label("\u56fa\u4f53\u8fb9\u754c\uff08\u7fc5\u7247\uff09");
    model.component("comp1").selection("sel2")
         .set(85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").label("\u6d41\u4f53\u8fb9\u754c\uff08\u4f53\uff09");
    model.component("comp1").selection("sel3")
         .set(49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").label("\u56fa\u4f53\u8fb9\u754c\uff08\u4f53\uff09");
    model.component("comp1").selection("sel4")
         .set(109, 110, 111, 112, 113, 114, 116, 117, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u6240\u6709\u6d41\u4f53\u8fb9\u754c");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel3"});
    model.component("comp1").selection().duplicate("sel5", "sel1");
    model.component("comp1").selection("sel5").label("\u6ed1\u79fb\u7f51\u683c\u8fb9\u754c");
    model.component("comp1").selection("sel5")
         .set(49, 50, 53, 54, 55, 56, 57, 58, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72);

    model.component("comp1").physics("spf").selection().set(1, 2, 3, 4);
    model.component("comp1").physics("spf").feature("wallbc1").set("BoundaryCondition", "Slip");
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(1);
    model.component("comp1").physics("mbd").selection().set(5, 6, 7);
    model.component("comp1").physics("mbd").create("rd1", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd1").label("\u521a\u6027\u6750\u6599\uff1a\u4f53");
    model.component("comp1").physics("mbd").feature("rd1").selection().set(7);
    model.component("comp1").physics("mbd").create("att1", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att1").label("\u8fde\u63a5\u4ef6\uff1a\u7fc5\u7247 1");
    model.component("comp1").physics("mbd").feature("att1").selection().set(107);
    model.component("comp1").physics("mbd").feature().duplicate("att2", "att1");
    model.component("comp1").physics("mbd").feature("att2").label("\u8fde\u63a5\u4ef6\uff1a\u7fc5\u7247 2");
    model.component("comp1").physics("mbd").feature("att2").selection().set(108);
    model.component("comp1").physics("mbd").create("hgj1", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj1").set("Source", "rd1");
    model.component("comp1").physics("mbd").feature("hgj1").set("Destination", "att1");
    model.component("comp1").physics("mbd").feature("hgj1").set("CenterOfJointType", "UserDefined");
    model.component("comp1").physics("mbd").feature("hgj1").set("e", new int[]{0, 0, 1});
    model.component("comp1").physics("mbd").feature("hgj1").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("hgj1").feature("pm1").set("thp", "-th");
    model.component("comp1").physics("mbd").feature().duplicate("hgj2", "hgj1");
    model.component("comp1").physics("mbd").feature("hgj2").set("Destination", "att2");
    model.component("comp1").physics("mbd").feature("hgj2").feature("pm1").set("thp", "th");

    model.component("comp1").common("free1").selection().set(1, 2, 3, 4);
    model.component("comp1").common().create("disp1", "PrescribedMeshDisplacement");
    model.component("comp1").common("disp1").selection().named("uni1");
    model.component("comp1").common("disp1")
         .set("prescribedMeshDisplacement", new String[]{"fsip1.u_solid", "fsip1.v_solid", "fsip1.w_solid"});
    model.component("comp1").common().create("pnmd1", "PrescribedNormalMeshDisplacement");
    model.component("comp1").common("pnmd1").selection().named("sel5");
    model.component("comp1").common("pnmd1").set("prescribedNormalDisplacement", "un_solid");
    model.component("comp1").common("pnmd1").set("constraintType", "Nitsche");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat1").label("Water, liquid");
    model.component("comp1").material("mat1").set("family", "water");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat2").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat2").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat2").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat2").label("Structural steel");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("fresnel", 0.9);
    model.component("comp1").material("mat2").set("roughness", 0.3);
    model.component("comp1").material("mat2").set("diffusewrap", 0);
    model.component("comp1").material("mat2").set("reflectance", 0);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat2").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat2").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat2").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat2").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("Norton").label("Norton");
    model.component("comp1").material("mat2").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat2").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat2").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.component("comp1").material("mat1").selection().set(1, 2, 3, 4);
    model.component("comp1").material("mat2").selection().set(5, 6, 7);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size3").selection().named("sel4");
    model.component("comp1").mesh("mesh1").feature("size3").set("custom", false);
    model.component("comp1").mesh("mesh1").feature("size3").set("hauto", 4);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").multiphysics("fsip1").set("pairs", new String[]{"ap1", "ap2"});

    model.study("std1").feature("time").set("tlist", "range(0,0.01,0.3) range(0.32,0.02,1)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", 0.005);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "intermediate");
    model.sol("sol1").feature("t1").set("bwinitstepfrac", 0.01);
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("subjtech", "onevery");

    model.study("std1").createAutoSequences("sol");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u5207\u9762");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection().set(1, 2, 3, 4, 5, 7, 8, 10, 11, 14, 15, 16, 81, 82, 83, 84);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u4f4d\u79fb (mbd)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("expr", "mbd.disp");
    model.result("pg3").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u901f\u5ea6 (mbd)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("vol1", "Volume");
    model.result("pg4").feature("vol1").label("\u57df\u7f16\u53f7");
    model.result("pg4").feature("vol1").set("descractive", true);
    model.result("pg4").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg4").feature("vol1").set("descr", "\u57df\u7f16\u53f7");
    model.result("pg4").feature("vol1").set("rangecoloractive", "on");
    model.result("pg4").feature("vol1").set("rangecolormin", -0.5);
    model.result("pg4").feature("vol1").set("rangecolormax", 9.5);
    model.result("pg4").feature("vol1").set("colortable", "Cyclic");
    model.result("pg4").feature("vol1").set("colorlegend", false);
    model.result("pg4").feature("vol1").set("colortabletype", "discrete");
    model.result("pg4").feature("vol1").set("smooth", "none");
    model.result("pg4").feature("vol1").set("data", "parent");
    model.result("pg4").feature().create("arwl1", "ArrowLine");
    model.result("pg4").feature("arwl1").label("\u7ebf\u4e0a\u7bad\u5934");
    model.result("pg4").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY", "mbd.u_tZ"});
    model.result("pg4").feature("arwl1").set("placement", "elements");
    model.result("pg4").feature("arwl1").set("data", "parent");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").label("\u52a8\u7f51\u683c");
    model.result("pg5").create("mesh1", "Mesh");
    model.result("pg5").feature("mesh1").set("meshdomain", "volume");
    model.result("pg5").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg5").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg5").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg5").feature("mesh1").feature("sel1").selection().set(1, 2, 3, 4, 5, 6, 7);
    model.result("pg5").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg5").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg5").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();

    model.study("std1").feature("time").set("plot", true);
    model.study("std1").feature("time").set("plotgroup", "pg3");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 66, 0);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 66, 0);
    model.result("pg2").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 66, 0);
    model.result("pg4").run();
    model.result("pg4").feature("arwl1").set("scaleactive", true);
    model.result("pg4").feature("arwl1").set("scale", 35);
    model.result("pg4").feature("arwl1").set("placement", "uniform");
    model.result("pg4").feature("arwl1").set("arrowcount", 4000);
    model.result("pg4").feature("arwl1").set("color", "cyan");
    model.result("pg4").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").add("plotgroup", "pg5");
    model.nodeGroup("grp1").label("\u9ed8\u8ba4\u7ed8\u56fe");

    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u6d41\u4f53\u538b\u529b (xy) & \u56fa\u4f53\u4f4d\u79fb");
    model.result("pg6").setIndex("looplevel", 31, 0);
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u56fa\u4f53\u4f4d\u79fb");
    model.result("pg6").feature("surf1").set("expr", "mbd.disp");
    model.result("pg6").feature("surf1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg6").feature("surf1").set("rangecoloractive", true);
    model.result("pg6").feature("surf1").set("rangecolormax", 1.6);
    model.result("pg6").feature("surf1").set("colortable", "Twilight");
    model.result("pg6").feature().duplicate("surf2", "surf1");
    model.result("pg6").run();
    model.result("pg6").feature("surf2").label("\u538b\u529b");
    model.result("pg6").feature("surf2").set("expr", "p");
    model.result("pg6").feature("surf2").set("descr", "\u538b\u529b");
    model.result("pg6").feature("surf2").set("rangecolormin", -1);
    model.result("pg6").feature("surf2").set("rangecolormax", 1);
    model.result("pg6").feature("surf2").set("colortable", "Rainbow");
    model.result("pg6").feature("surf2").create("sel1", "Selection");
    model.result("pg6").feature("surf2").feature("sel1").selection().set(6, 13);
    model.result("pg6").run();
    model.result("pg6").create("con1", "Contour");
    model.result("pg6").feature("con1").label("\u538b\u529b\u7b49\u503c\u7ebf");
    model.result("pg6").feature("con1").set("expr", "p");
    model.result("pg6").feature("con1").set("descr", "\u538b\u529b");
    model.result("pg6").feature("con1").set("titletype", "none");
    model.result("pg6").feature("con1").set("coloring", "uniform");
    model.result("pg6").feature("con1").set("color", "white");
    model.result("pg6").feature("con1").set("colorlegend", false);
    model.result("pg6").feature("con1").create("sel1", "Selection");
    model.result("pg6").feature("con1").feature("sel1").selection().set(6, 13);
    model.result("pg6").run();
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").label("\u6d41\u4f53\u901f\u5ea6");
    model.result("pg6").feature("arws1").set("scaleactive", true);
    model.result("pg6").feature("arws1").set("scale", 20);
    model.result("pg6").feature("arws1").set("arrowcount", 4000);
    model.result("pg6").feature("arws1").create("sel1", "Selection");
    model.result("pg6").feature("arws1").feature("sel1").selection().set(6, 13);
    model.result("pg6").run();
    model.result("pg6").create("arwl1", "ArrowLine");
    model.result("pg6").feature("arwl1").label("\u56fa\u4f53\u901f\u5ea6");
    model.result("pg6").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY", "mbd.u_tZ"});
    model.result("pg6").feature("arwl1").set("descr", "\u901f\u5ea6");
    model.result("pg6").feature("arwl1").set("scaleactive", true);
    model.result("pg6").feature("arwl1").set("scale", 35);
    model.result("pg6").feature("arwl1").set("arrowcount", 50);
    model.result("pg6").feature("arwl1").set("color", "magenta");
    model.result("pg6").feature("arwl1").create("sel1", "Selection");
    model.result("pg6").feature("arwl1").feature("sel1").selection().set(185, 188, 228, 249, 256, 261, 266, 269);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 21, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 26, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 41, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 66, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 31, 0);
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u6d41\u4f53\u538b\u529b (xz) & \u56fa\u4f53\u4f4d\u79fb");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").feature("sel1").selection().set(9, 12);
    model.result("pg7").run();
    model.result("pg7").feature("con1").feature("sel1").selection().set(9, 12);
    model.result("pg7").run();
    model.result("pg7").feature("arws1").set("scale", 100);
    model.result("pg7").run();
    model.result("pg7").feature("arws1").feature("sel1").selection().set(9, 12);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").run();
    model.result("pg8").label("\u53d8\u5f62\u7f51\u683c");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").create("mesh1", "Mesh");
    model.result("pg8").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg8").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg8").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg8").feature("mesh1").set("meshdomain", "volume");
    model.result("pg8").feature("mesh1").set("elemcolor", "gray");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevel", 1, 0);
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevel", 66, 0);
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").label("\u7fc5\u7247\u65cb\u8f6c");
    model.result("pg9").feature("glob1").setIndex("expr", "th*180/pi", 0);
    model.result("pg9").feature("glob1").setIndex("descr", "\u7fc5\u7247\u65cb\u8f6c", 0);
    model.result("pg9").feature().duplicate("glob2", "glob1");
    model.result("pg9").run();
    model.result("pg9").feature("glob2").label("\u524d\u8fdb\u901f\u5ea6");
    model.result("pg9").feature("glob2").setIndex("expr", "mbd.rd1.u_tx", 0);
    model.result("pg9").feature("glob2").setIndex("unit", "cm/s", 0);
    model.result("pg9").feature("glob2").setIndex("descr", "\u524d\u8fdb\u901f\u5ea6", 0);
    model.result("pg9").feature("glob2").set("linestyle", "dashed");
    model.result("pg9").run();
    model.result("pg9").label("\u7fc5\u7247\u65cb\u8f6c & \u901f\u5ea6");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u7fc5\u7247\u65cb\u8f6c (deg)");
    model.result("pg9").set("twoyaxes", true);
    model.result("pg9").setIndex("plotonsecyaxis", true, 1, 1);
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").label("\u6d41\u4f53\u538b\u529b (xy) & \u56fa\u4f53\u4f4d\u79fb");
    model.result().export("anim1").set("plotgroup", "pg6");
    model.result().export().duplicate("anim2", "anim1");
    model.result().export("anim2").showFrame();
    model.result().export("anim2").label("\u6d41\u4f53\u538b\u529b (xz) & \u56fa\u4f53\u4f4d\u79fb");
    model.result().export("anim2").set("plotgroup", "pg7");
    model.result().export().duplicate("anim3", "anim2");
    model.result().export("anim3").showFrame();
    model.result().export("anim3").label("\u53d8\u5f62\u7f51\u683c");
    model.result().export("anim3").set("plotgroup", "pg8");
    model.result("pg6").run();

    model.title("\u6db2\u6d78\u673a\u6784");

    model
         .description("\u672c\u4f8b\u9610\u8ff0\u5982\u4f55\u6a21\u62df\u6d78\u6ca1\u5728\u6d41\u4f53\u4e2d\u7684\u591a\u4f53\u673a\u6784\uff0c\u8fd9\u79cd\u673a\u6784\u7531\u4e24\u4e2a\u901a\u8fc7\u94f0\u94fe\u5173\u8282\u8fde\u63a5\u5230\u4e2d\u5fc3\u521a\u4f53\u7684\u7fc5\u7247\u7ec4\u6210\u3002\u901a\u8fc7\u6c42\u89e3\u77ac\u6001\u6d41-\u56fa\u8026\u5408\u95ee\u9898\u6765\u6a21\u62df\u673a\u6784\u7684\u6e38\u52a8\u7279\u6027\uff0c\u5176\u4e2d\u7fc5\u7247\u65cb\u8f6c\u4ea7\u751f\u524d\u8fdb\u901f\u5ea6\u3002\n\n\u672c\u4f8b\u5728\u6d41\u4f53\u4e0e\u56fa\u4f53\u8fb9\u754c\u4e4b\u95f4\u7684\u5bf9\u4e0a\u8fdb\u884c\u6d41-\u56fa\u8026\u5408\u5efa\u6a21\uff0c\u5176\u4e2d\u5047\u8bbe\u6d41\u4f53\u6d41\u52a8\u4e0e\u673a\u6784\u7684\u591a\u4f53\u6a21\u578b\u4e4b\u95f4\u4e3a\u53cc\u5411\u8026\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("mechanism_submerged_in_fluid.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
