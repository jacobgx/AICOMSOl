/*
 * truss_tower_buckling_guys.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:02 by COMSOL 6.3.0.290. */
public class truss_tower_buckling_guys {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Buckling_and_Wrinkling");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("truss", "Truss", "geom1");
    model.component("comp1").physics().create("wire", "Wire", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/truss", true);
    model.study("std1").feature("stat").setSolveFor("/physics/wire", true);
    model.study("std1").create("buckling", "LinearBuckling");
    model.study("std1").feature("buckling").set("neigsactive", true);
    model.study("std1").feature("buckling").set("solnum", "auto");
    model.study("std1").feature("buckling").set("notsolnum", "auto");
    model.study("std1").feature("buckling").set("outputmap", new String[]{});
    model.study("std1").feature("buckling").set("ngenAUX", "1");
    model.study("std1").feature("buckling").set("goalngenAUX", "1");
    model.study("std1").feature("buckling").set("ngenAUX", "1");
    model.study("std1").feature("buckling").set("goalngenAUX", "1");
    model.study("std1").feature("buckling").setSolveFor("/physics/truss", true);
    model.study("std1").feature("buckling").setSolveFor("/physics/wire", true);

    model.param().label("\u5854\u67b6\u51e0\u4f55\u53c2\u6570");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("depth", "0.4[m]", "\u5854\u6df1\u5ea6");
    model.param().set("width", "0.45[m]", "\u5854\u5bbd\u5ea6");
    model.param().set("height", "1[m]", "\u5854\u9ad8\u5ea6");
    model.param().set("n", "10", "\u622a\u9762\u6570");
    model.param().set("L", "height*(2*n-1)", "\u5854\u603b\u9ad8\u5ea6");
    model.param().set("do1", "3[cm]", "\u7ba1 1 \u7684\u5916\u5f84");
    model.param().set("di1", "2[cm]", "\u7ba1 1 \u7684\u5185\u5f84");
    model.param().set("do2", "2[cm]", "\u7ba1 2 \u7684\u5916\u5f84");
    model.param().set("di2", "1.4[cm]", "\u7ba1 2 \u7684\u5185\u5f84");
    model.param().set("A1", "pi/4*(do1^2-di1^2)", "\u7ba1 1 \u7684\u9762\u79ef");
    model.param().set("A2", "pi/4*(do2^2-di2^2)", "\u7ba1 2 \u7684\u9762\u79ef");
    model.param().create("par2");
    model.param("par2").label("\u62c9\u7ebf\u51e0\u4f55\u53c2\u6570");
    model.param("par2").set("gnd_attach_guy1", "6[m]");
    model.param("par2").descr("gnd_attach_guy1", "\u62c9\u7ebf 1 \u7684\u5730\u9762\u9644\u7740\u8ddd\u79bb");
    model.param("par2").set("gnd_attach_guy2", "12[m]");
    model.param("par2").descr("gnd_attach_guy2", "\u62c9\u7ebf 2 \u7684\u5730\u9762\u9644\u7740\u8ddd\u79bb");
    model.param("par2").set("guy_d", "5[mm]");
    model.param("par2").descr("guy_d", "\u62c9\u7ebf 1 \u548c\u62c9\u7ebf 2 \u7684\u76f4\u5f84");
    model.param("par2").set("guy_height1", "7[m]");
    model.param("par2").descr("guy_height1", "\u62c9\u7ebf 1 \u7684\u9ad8\u5ea6");
    model.param("par2").set("guy_height2", "12[m]");
    model.param("par2").descr("guy_height2", "\u62c9\u7ebf 2 \u7684\u9ad8\u5ea6");
    model.param("par2").set("guy_area", "pi/4*guy_d^2");
    model.param("par2").descr("guy_area", "\u62c9\u7ebf 1 \u548c\u62c9\u7ebf 2 \u7684\u9762\u79ef");
    model.param("par2").set("kA", "3.927e6[N]");
    model.param("par2").descr("kA", "\u8f74\u5411\u521a\u5ea6");
    model.param("par2").set("rhoL", "0.15413[kg/m]");
    model.param("par2").descr("rhoL", "\u5355\u4f4d\u957f\u5ea6\u7684\u8d28\u91cf");

    model.component("comp1").geom("geom1").insertFile("truss_tower_buckling.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("blk1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("ccur1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("pol1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("ls1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("ls2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("mir1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("arr1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("arr2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").label("\u5854\u67b6");
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").create("ls3", "LineSegment");
    model.component("comp1").geom("geom1").nodeGroup("grp1").remove("ls3", false);
    model.component("comp1").geom("geom1").feature("ls3").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls3")
         .set("coord1", new String[]{"0", "0", "guy_height1-mod(guy_height1,height)"});
    model.component("comp1").geom("geom1").feature("ls3").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls3")
         .set("coord2", new String[]{"-gnd_attach_guy1*width/sqrt(width^2+depth^2)", "0", "0"});
    model.component("comp1").geom("geom1").feature("ls3")
         .setIndex("coord2", "-gnd_attach_guy1*depth/sqrt(width^2+depth^2)", 1);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u62c9\u7ebf 1");
    model.component("comp1").geom("geom1").feature("ls3").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("ls4", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls4").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls4")
         .set("coord1", new String[]{"0", "0", "guy_height2-mod(guy_height2,height)"});
    model.component("comp1").geom("geom1").feature("ls4").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls4")
         .set("coord2", new String[]{"-gnd_attach_guy2*width/sqrt(width^2+depth^2)", "0", "0"});
    model.component("comp1").geom("geom1").feature("ls4")
         .setIndex("coord2", "-gnd_attach_guy2*depth/sqrt(width^2+depth^2)", 1);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u62c9\u7ebf 2");
    model.component("comp1").geom("geom1").feature("ls4").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("mir2", "Mirror");
    model.component("comp1").geom("geom1").feature("mir2").selection("input").set("ls3", "ls4");
    model.component("comp1").geom("geom1").feature("mir2").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir2").set("pos", new String[]{"width/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("mir2").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("mir3", "Mirror");
    model.component("comp1").geom("geom1").feature("mir3").selection("input").set("ls3", "ls4", "mir2");
    model.component("comp1").geom("geom1").feature("mir3").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir3").set("pos", new String[]{"0", "depth/2", "0"});
    model.component("comp1").geom("geom1").feature("mir3").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").nodeGroup().create("grp2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("ls3");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("ls4");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("mir2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("mir3");
    model.component("comp1").geom("geom1").nodeGroup("grp2").label("\u62c9\u7ebf");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u5782\u76f4\u8fb9");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(5, 112, 180, 240);
    model.component("comp1").selection("sel1").set("groupcontang", true);
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("\u6a2a\u5411\u6841\u67b6\u8fb9");
    model.component("comp1").selection("com1").set("entitydim", 1);
    model.component("comp1").selection("com1")
         .set("input", new String[]{"sel1", "geom1_csel1_edg", "geom1_csel2_edg"});
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u5854\u67b6");
    model.component("comp1").selection("uni1").set("entitydim", 1);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "com1"});
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u62c9\u7ebf");
    model.component("comp1").selection("uni2").set("entitydim", 1);
    model.component("comp1").selection("uni2").set("input", new String[]{"geom1_csel1_edg", "geom1_csel2_edg"});

    model.component("comp1").physics("truss").selection().named("uni1");
    model.component("comp1").physics("truss").feature("csd1")
         .label("\u6a2a\u622a\u9762\u6570\u636e\uff08\u5782\u76f4\u8fb9\uff09");
    model.component("comp1").physics("truss").feature("csd1").set("SectionType", "PipeSection");
    model.component("comp1").physics("truss").feature("csd1").set("do_pipe", "do1");
    model.component("comp1").physics("truss").feature("csd1").set("di_pipe", "di1");
    model.component("comp1").physics("truss").create("csd2", "CrossSectionTruss", 1);
    model.component("comp1").physics("truss").feature("csd2").selection().named("com1");
    model.component("comp1").physics("truss").feature("csd2").set("SectionType", "PipeSection");
    model.component("comp1").physics("truss").feature("csd2").set("do_pipe", "do2");
    model.component("comp1").physics("truss").feature("csd2").set("di_pipe", "di2");
    model.component("comp1").physics("truss").feature("csd2")
         .label("\u6a2a\u622a\u9762\u6570\u636e\uff08\u5176\u4ed6\u6841\u67b6\u8fb9\uff09");
    model.component("comp1").physics("truss").create("pin1", "Pinned", 0);
    model.component("comp1").physics("truss").feature("pin1").selection().set(5, 25, 45, 65);
    model.component("comp1").physics("truss").create("gacc1", "GravityAcceleration", -1);
    model.component("comp1").physics("truss").create("pl1", "PointLoad", 0);
    model.component("comp1").physics("truss").feature("pl1").selection().set(24, 44, 64, 84);
    model.component("comp1").physics("truss").feature("pl1").set("forceType", "TotalForce");
    model.component("comp1").physics("truss").feature("pl1").set("force", new String[]{"0", "0", "-1[N]"});
    model.component("comp1").physics("wire").selection().named("uni2");
    model.component("comp1").physics("wire").field("displacement").field("u");
    model.component("comp1").physics("wire").feature("elw1").set("area", "guy_area");
    model.component("comp1").physics("wire").feature("elw1").create("iss1", "InitialStressandStrain", 1);
    model.component("comp1").physics("wire").feature("elw1").feature("iss1").selection().named("uni2");
    model.component("comp1").physics("wire").feature("elw1").feature("iss1").set("Nxi", "4[kN]");
    model.component("comp1").physics("wire").create("pin1", "Pinned", 0);
    model.component("comp1").physics("wire").feature("pin1").selection().set(1, 2, 3, 4, 85, 86, 87, 88);
    model.component("comp1").physics("wire").create("gacc1", "GravityAcceleration", -1);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat1").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat1").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat1").label("Structural steel");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.3);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat1").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Norton").label("Norton");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.component("comp1").material("mat1").selection().named("uni1");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u7ebf\u7f06\u6750\u6599");
    model.component("comp1").material("mat2").selection().named("uni2");
    model.component("comp1").material("mat2").propertyGroup().create("ElasticWire", "ElasticWire", "Elastic_wire");
    model.component("comp1").material("mat2").propertyGroup("ElasticWire").set("k_A", new String[]{"kA"});
    model.component("comp1").material("mat2").propertyGroup("ElasticWire").set("rho_L", new String[]{"rhoL"});

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().all();
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").selection().named("geom1_csel1_edg");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("numelem", 10);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis3").selection().named("geom1_csel2_edg");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis3").set("numelem", 10);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature().move("stat2", 1);
    model.study("std1").feature("stat").label("\u7a33\u6001\uff0c\u4ec5\u9759\u8f7d");
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"truss/pl1"});
    model.study("std1").feature("stat2").label("\u7a33\u6001\uff0c\u9759\u8f7d + \u6d3b\u8f7d");
    model.study("std1").showAutoSequences("all");
    model.study("std1").feature("buckling").set("neigs", 2);
    model.study("std1").feature("buckling").set("uselinpsol", true);
    model.study("std1").feature("buckling").set("linpsoluse", "sol2");
    model.study("std1").feature("buckling").set("useliveloadsol", true);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("frametype", "spatial");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", new String[]{"truss.disp"});
    model.result("pg1").feature("line1").set("threshold", "manual");
    model.result("pg1").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("line1").set("colortable", "Rainbow");
    model.result("pg1").feature("line1").set("colortabletrans", "none");
    model.result("pg1").feature("line1").set("colorscalemode", "linear");
    model.result("pg1").label("\u632f\u578b (truss)");
    model.result("pg1").feature("line1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", "truss.re");
    model.result("pg1").feature("line1").set("resolution", "extrafine");
    model.result("pg1").feature("line1").set("smooth", "internal");
    model.result("pg1").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line1").set("tuberadiusscale", 1);
    model.result("pg1").feature("line1").set("tubeendcaps", false);
    model.result("pg1").feature("line1").create("def", "Deform");
    model.result("pg1").feature("line1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset("dset1").set("frametype", "spatial");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegends", false);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", new String[]{"wire.disp"});
    model.result("pg2").feature("line1").set("threshold", "manual");
    model.result("pg2").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("line1").set("colortable", "Rainbow");
    model.result("pg2").feature("line1").set("colortabletrans", "none");
    model.result("pg2").feature("line1").set("colorscalemode", "linear");
    model.result("pg2").label("\u632f\u578b (wire)");
    model.result("pg2").feature("line1").set("colortable", "AuroraBorealis");
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("radiusexpr", "wire.re");
    model.result("pg2").feature("line1").set("resolution", "extrafine");
    model.result("pg2").feature("line1").set("smooth", "internal");
    model.result("pg2").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("line1").set("tuberadiusscale", 1);
    model.result("pg2").feature("line1").set("tubeendcaps", false);
    model.result("pg2").feature("line1").create("def", "Deform");
    model.result("pg2").feature("line1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("line1").set("tuberadiusscale", 6);
    model.result("pg2").run();
    model.result("pg1").run();
    model.result("pg1").feature().copy("line2", "pg2/line1");
    model.result("pg1").run();
    model.result("pg1").feature("line2").set("inheritplot", "line1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").stepNext(0);
    model.result("pg1").run();
    model.result("pg1").stepPrevious(0);
    model.result("pg1").run();

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1")
         .set("lbs_f", "(1-withsol('sol2',truss.lbf_i))/(withsol('sol3',truss.lbf_i)-withsol('sol2',truss.lbf_i))", "\u5c40\u90e8\u5c48\u66f2\u5b89\u5168\u7cfb\u6570");
    model.component("comp1").variable("var1")
         .set("lbf_i", "1/lbs_f", "\u5c40\u90e8\u5c48\u66f2\u5931\u6548\u6307\u6570");

    model.sol("sol1").updateSolution();

    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u5c40\u90e8\u5c48\u66f2");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", "lbf_i");
    model.result("pg3").feature("line1").set("colorlegend", false);
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("radiusexpr", "truss.re");
    model.result("pg3").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg3").feature("line1").set("tuberadiusscale", 2);
    model.result("pg3").feature("line1").set("smooth", "none");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("mml1", "MaxMinLine");
    model.result("pg3").feature("mml1").set("expr", "lbs_f");
    model.result("pg3").feature("mml1").set("display", "min");
    model.result("pg3").feature("mml1").set("labelprefix", "\u5b89\u5168\u7cfb\u6570");
    model.result("pg3").run();
    model.result("pg1").run();

    model.title("\u9759\u8f7d\u4f5c\u7528\u4e0b\u7684\u6841\u67b6\u5854\u7ebf\u6027\u5c48\u66f2\u5206\u6790");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u8ba1\u7b97\u5177\u6709\u9759\u8f7d\u65f6\u7684\u4e34\u754c\u5c48\u66f2\u8f7d\u8377\u3002\u6841\u67b6\u5854\u7531\u9884\u5f20\u7d27\u7684\u62c9\u7d22\u652f\u6491\uff0c\u5176\u4e2d\u5c06\u7ed3\u6784\u4e0a\u7684\u9884\u5f20\u529b\u8f7d\u8377\u548c\u91cd\u529b\u89c6\u4e3a\u9759\u8f7d\uff0c\u800c\u5c06\u4f5c\u7528\u4e8e\u5854\u9876\u7684\u5782\u76f4\u8f7d\u8377\u89c6\u4e3a\u6d3b\u8f7d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("truss_tower_buckling_guys.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
