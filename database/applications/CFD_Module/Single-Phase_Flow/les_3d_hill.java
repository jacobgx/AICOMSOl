/*
 * les_3d_hill.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class les_3d_hill {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Single-Phase_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LESRBVM", "geom1");
    model.component("comp1").physics().create("ipf", "IncompressiblePotentialFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ipf", true);

    model.param().set("H", "1[m]");
    model.param().descr("H", "\u5c0f\u4e18\u9ad8\u5ea6");
    model.param().set("ReH", "1.3e5");
    model.param().descr("ReH", "\u7531\u9ad8\u5ea6\u4f30\u7b97\u7684\u96f7\u8bfa\u6570");
    model.param().set("rho", "1[kg/m^3]");
    model.param().descr("rho", "\u6d41\u4f53\u7684\u5bc6\u5ea6");
    model.param().set("U", "2[m/s]");
    model.param().descr("U", "\u6d41\u4f53\u7684\u901f\u5ea6");
    model.param().set("mu", "rho*U*H/ReH");
    model.param().descr("mu", "\u6d41\u4f53\u7684\u9ecf\u5ea6");
    model.param().set("LTin", "0.1*H");
    model.param().descr("LTin", "\u5165\u53e3\u5904\u7684\u6e4d\u6d41\u957f\u5ea6\u5c3a\u5ea6");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").label("\u8f74\u5bf9\u79f0\u5c0f\u4e18\u8f6e\u5ed3");
    model.component("comp1").func("an1").set("funcname", "hill");
    model.component("comp1").func("an1")
         .set("expr", "-(besselj(0,3.1962)*besseli(0, 3.1962*x/(2*H))-besseli(0,3.1962)*besselj(0, 3.1962*x/(2*H)))*H/6.04844");
    model.component("comp1").func("an1").setIndex("plotargs", "2*H", 0, 2);

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"19.8*H", "3.2*H", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "11.7*H", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"-4.1*H", "0", "-11.7*H/2"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", "4.5*H");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"19.8*H", "4.5*H"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"-4.1*H", "-2.25*H"});
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"-4.1*H", "0", "0"});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"5*H", "0", "0"});
    model.component("comp1").geom("geom1").feature("ls1").set("selresult", true);
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "zy");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("pc1", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pc1").set("parmax", "2*H");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pc1")
         .set("coord", new String[]{"s", "hill(s)"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("pc1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls1")
         .set("coord2", new String[]{"2*H", "0"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls2")
         .set("coord2", new String[]{"0", "H"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("ls2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("csol1").selection("input")
         .set("ls1", "ls2", "pc1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("rev1").selection("input").set("wp2");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "full");
    model.component("comp1").geom("geom1").run("rev1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1", "ls1", "wp1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("rev1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input")
         .set("fin", 5, 9, 15, 16, 17, 19, 29, 30, 31, 33);
    model.component("comp1").geom("geom1").run("mce1");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("Cp \u56fe\u7684\u8fb9");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(5, 13, 18, 19);

    model.component("comp1").physics("ipf").prop("PressureProperty").set("UScale", "U");
    model.component("comp1").physics("ipf").prop("ShapeProperty").set("order_velocitypotential", 1);
    model.component("comp1").physics("ipf").feature("fp1").set("rho_mat", "userdef");
    model.component("comp1").physics("ipf").feature("fp1").set("rho", "rho");
    model.component("comp1").physics("ipf").create("velbc1", "Velocity", 2);
    model.component("comp1").physics("ipf").feature("velbc1").selection().set(1);
    model.component("comp1").physics("ipf").feature("velbc1").set("Uin", "U");
    model.component("comp1").physics("ipf").create("open1", "OpenBoundary", 2);
    model.component("comp1").physics("ipf").feature("open1").selection().set(10);
    model.component("comp1").physics("spf").prop("TurbulenceModelProperty").set("LESWallTreatment", "Automatic");
    model.component("comp1").physics("spf").feature("fp1").set("rho_mat", "userdef");
    model.component("comp1").physics("spf").feature("fp1").set("rho", "rho");
    model.component("comp1").physics("spf").feature("fp1").set("mu_mat", "userdef");
    model.component("comp1").physics("spf").feature("fp1").set("mu", "mu");
    model.component("comp1").physics("spf").feature().duplicate("init2", "init1");
    model.component("comp1").physics("spf").feature("init2").set("u_init", new String[]{"ipf.u", "ipf.v", "ipf.w"});
    model.component("comp1").physics("spf").feature("init2").set("p_init", "ipf.p");
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 2);
    model.component("comp1").physics("spf").feature("wallbc2").set("BoundaryCondition", "Slip");
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(3, 4);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(1);
    model.component("comp1").physics("spf").feature("inl1")
         .set("U0in", "U*abs(y/(3.2*H))^(1/7)*abs(1-(y/(3.2*H)))^(1/7)/0.75644");
    model.component("comp1").physics("spf").feature("inl1").set("includeSyntheticTurb", true);
    model.component("comp1").physics("spf").feature("inl1").set("IT_list", "low_turbulent_intensity");
    model.component("comp1").physics("spf").feature("inl1").set("LT_list", "user_defined");
    model.component("comp1").physics("spf").feature("inl1").set("LT", "LTin");
    model.component("comp1").physics("spf").feature("inl1").set("N", 600);
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().set(10);
    model.component("comp1").physics("spf").feature("out1").set("SuppressBackflow", false);

    model.component("comp1").mesh("mesh1").contribute("physics/spf", false);
    model.component("comp1").mesh("mesh1").contribute("physics/ipf", false);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "0.1222*H");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "0.0390*H");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(2, 11, 12, 13, 16);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(4, 24, 36, 38);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 25);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 25);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 2.5);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature().duplicate("dis3", "dis2");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(25);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("reverse", false);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(7, 9, 14);
    model.component("comp1").mesh("mesh1").create("cpf1", "CopyFace");
    model.component("comp1").mesh("mesh1").feature("cpf1").selection("source").geom(2);
    model.component("comp1").mesh("mesh1").feature("cpf1").selection("destination").geom(2);
    model.component("comp1").mesh("mesh1").feature("cpf1").selection("source").set(7, 9, 14);
    model.component("comp1").mesh("mesh1").feature("cpf1").selection("destination").set(6, 8, 15);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemcount", 82);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemratio", 82);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").active(false);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").label("\u7814\u7a76 1: \u7a33\u6001\u52bf\u6d41\u89e3");
    model.study("std1").feature("stat").setEntry("activate", "spf", false);

    model.sol().create("sol1");

    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1);
    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1);
    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1);

    model.sol("sol1").study("std1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "stat");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "stat");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("s1").create("se1", "Segregated");
    model.sol("sol1").feature("s1").feature("se1").feature().remove("ssDef");
    model.sol("sol1").feature("s1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("segvar", new String[]{"comp1_phi"});
    model.sol("sol1").feature("s1").create("i1", "Iterative");
    model.sol("sol1").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol1").feature("s1").feature("i1").set("prefuntype", "left");
    model.sol("sol1").feature("s1").feature("i1").set("itrestart", 50);
    model.sol("sol1").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol1").feature("s1").feature("i1").set("maxlinit", 1000);
    model.sol("sol1").feature("s1").feature("i1").set("nlinnormuse", "on");
    model.sol("sol1").feature("s1").feature("i1").label("AMG\uff0c\u52bf\u6d41 (ipf)");
    model.sol("sol1").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("linsolver", "i1");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").label("\u901f\u5ea6\u52bf");
    model.sol("sol1").feature("s1").feature("se1").create("ss2", "SegregatedStep");
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("segvar", new String[]{"comp1_uPhi"});
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("subdamp", 1);
    model.sol("sol1").feature("s1").create("i2", "Iterative");
    model.sol("sol1").feature("s1").feature("i2").set("linsolver", "gmres");
    model.sol("sol1").feature("s1").feature("i2").set("prefuntype", "left");
    model.sol("sol1").feature("s1").feature("i2").set("itrestart", 50);
    model.sol("sol1").feature("s1").feature("i2").set("rhob", 400);
    model.sol("sol1").feature("s1").feature("i2").set("maxlinit", 1000);
    model.sol("sol1").feature("s1").feature("i2").set("nlinnormuse", "on");
    model.sol("sol1").feature("s1").feature("i2").label("AMG\uff0c\u52bf\u6d41\u7684\u5e73\u6ed1\u901f\u5ea6 (ipf)");
    model.sol("sol1").feature("s1").feature("i2").create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("linsolver", "i2");
    model.sol("sol1").feature("s1").feature("se1").feature("ss2")
         .label("\u52bf\u6d41\u7684\u5e73\u6ed1\u901f\u5ea6");
    model.sol("sol1").feature("s1").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("d1").label("\u76f4\u63a5\uff0c\u52bf\u6d41 (ipf)");
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"spf/init2"});
    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/spf", true);
    model.study("std2").feature("time").setSolveFor("/physics/ipf", false);
    model.study("std2").setGenPlots(false);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u77ac\u6001 LES \u89e3");
    model.study("std2").setStoreSolution(true);
    model.study("std2").feature("time").set("tlist", "range(0,20*H/U,20*H/U) range(20*H/U,(20*H/U)/400,40*H/U)");
    model.study("std2").feature("time").set("useinitsol", true);
    model.study("std2").feature("time").set("initstudy", "std1");
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").create("cmbsol", "CombineSolution");
    model.study("std2").feature("cmbsol").set("soloper", "remsol");
    model.study("std2").feature("cmbsol").set("solnum", "manual");
    model.study("std2").feature("cmbsol").set("manualsolnum", "1 2");
    model.study("std2").feature("cmbsol").set("clearsrcsol", true);

    model.sol().create("sol2");

    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1);

    model.sol("sol2").study("std2");
    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "time");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").set("control", "time");
    model.sol("sol2").create("t1", "Time");
    model.sol("sol2").feature("t1").set("tlist", "range(0,20*H/U,20*H/U) range(20*H/U,(20*H/U)/400,40*H/U)");
    model.sol("sol2").feature("t1").set("plot", "off");
    model.sol("sol2").feature("t1").set("plotgroup", "Default");
    model.sol("sol2").feature("t1").set("plotfreq", "tout");
    model.sol("sol2").feature("t1").set("probesel", "all");
    model.sol("sol2").feature("t1").set("probes", new String[]{});
    model.sol("sol2").feature("t1").set("probefreq", "tsteps");
    model.sol("sol2").feature("t1").set("rtol", 0.005);
    model.sol("sol2").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol2").feature("t1").set("atolglobalfactor", 0.05);
    model.sol("sol2").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol2").feature("t1")
         .set("atolmethod", new String[]{"comp1_p", "scaled", "comp1_phi", "global", "comp1_u", "global", "comp1_uPhi", "global"});
    model.sol("sol2").feature("t1")
         .set("atolvaluemethod", new String[]{"comp1_p", "factor", "comp1_phi", "factor", "comp1_u", "factor", "comp1_uPhi", "factor"});
    model.sol("sol2").feature("t1")
         .set("atolfactor", new String[]{"comp1_p", "1", "comp1_phi", "0.1", "comp1_u", "0.1", "comp1_uPhi", "0.1"});
    model.sol("sol2").feature("t1").set("reacf", true);
    model.sol("sol2").feature("t1").set("storeudot", true);
    model.sol("sol2").feature("t1").set("endtimeinterpolation", true);
    model.sol("sol2").feature("t1").set("timemethod", "genalpha");
    model.sol("sol2").feature("t1").set("estrat", "exclude");
    model.sol("sol2").feature("t1").set("rhoinf", 0.5);
    model.sol("sol2").feature("t1").set("predictor", "constant");
    model.sol("sol2").feature("t1").set("maxorder", 2);
    model.sol("sol2").feature("t1").set("stabcntrl", true);
    model.sol("sol2").feature("t1").set("maxstepconstraintgenalpha", "expr");
    model.sol("sol2").feature("t1").set("maxstepexpressiongenalpha", "comp1.spf.dt_CFL");
    model.sol("sol2").feature("t1").set("bwinitstepfrac", "0.01");
    model.sol("sol2").feature("t1").set("control", "time");
    model.sol("sol2").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("t1").feature("fc1").set("jtech", "once");
    model.sol("sol2").feature("t1").feature("fc1").set("damp", 0.9);
    model.sol("sol2").feature("t1").feature("fc1").set("ntolfact", 0.5);
    model.sol("sol2").feature("t1").feature("fc1").set("stabacc", "aacc");
    model.sol("sol2").feature("t1").feature("fc1").set("aaccdim", 5);
    model.sol("sol2").feature("t1").feature("fc1").set("aaccmix", 0.9);
    model.sol("sol2").feature("t1").feature("fc1").set("aaccdelay", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("maxiter", 8);
    model.sol("sol2").feature("t1").create("i1", "Iterative");
    model.sol("sol2").feature("t1").feature("i1").set("linsolver", "gmres");
    model.sol("sol2").feature("t1").feature("i1").set("prefuntype", "left");
    model.sol("sol2").feature("t1").feature("i1").set("itrestart", 50);
    model.sol("sol2").feature("t1").feature("i1").set("rhob", 20);
    model.sol("sol2").feature("t1").feature("i1").set("maxlinit", 100);
    model.sol("sol2").feature("t1").feature("i1").set("nlinnormuse", "on");
    model.sol("sol2").feature("t1").feature("i1").label("AMG\uff0c\u6d41\u4f53\u6d41\u52a8\u53d8\u91cf (spf)");
    model.sol("sol2").feature("t1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").set("maxcoarsedof", 80000);
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").set("strconn", 0.02);
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").set("compactaggregation", false);
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").feature("pr").create("sc1", "SCGS");
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("iter", 0);
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("scgsrelax", 0.7);
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsmethod", "lines");
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("relax", 0.5);
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgssolv", "stored");
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").feature("po").create("sc1", "SCGS");
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1").set("iter", 1);
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1").set("scgsrelax", 0.7);
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsmethod", "lines");
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1").set("relax", 0.5);
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgssolv", "stored");
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol2").feature("t1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol2").feature("t1").create("d1", "Direct");
    model.sol("sol2").feature("t1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol2").feature("t1").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol2").feature("t1").feature("d1")
         .label("\u76f4\u63a5\uff0c\u6d41\u4f53\u6d41\u52a8\u53d8\u91cf (spf)");
    model.sol("sol2").feature("t1").feature("fc1").set("linsolver", "i1");
    model.sol("sol2").feature("t1").feature("fc1").set("jtech", "once");
    model.sol("sol2").feature("t1").feature("fc1").set("damp", 0.9);
    model.sol("sol2").feature("t1").feature("fc1").set("ntolfact", 0.5);
    model.sol("sol2").feature("t1").feature("fc1").set("stabacc", "aacc");
    model.sol("sol2").feature("t1").feature("fc1").set("aaccdim", 5);
    model.sol("sol2").feature("t1").feature("fc1").set("aaccmix", 0.9);
    model.sol("sol2").feature("t1").feature("fc1").set("aaccdelay", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("maxiter", 8);
    model.sol("sol2").feature("t1").feature().remove("fcDef");
    model.sol("sol2").create("su1", "StoreSolution");
    model.sol("sol2").create("cms1", "CombineSolution");
    model.sol("sol2").feature("cms1").set("control", "cmbsol");
    model.sol("sol2").attach("std2");
    model.sol("sol2").feature("t1").set("maxstepconstraintgenalpha", "auto");
    model.sol("sol2").runAll();

    model.study().create("std3");
    model.study("std3").label("\u7814\u7a76 3\uff1a\u65f6\u5747 LES \u89e3");
    model.study("std3").setGenConv(false);
    model.study("std3").setGenPlots(false);
    model.study("std3").create("cmbsol", "CombineSolution");
    model.study("std3").feature("cmbsol").set("soloper", "wgtsum");
    model.study("std3").feature("cmbsol").set("cssolwghtsum", "sol2");
    model.study("std3").feature("cmbsol").set("weightoneexpr", "1.0/401");

    model.sol().create("sol4");
    model.sol("sol4").study("std3");
    model.sol("sol4").create("cms1", "CombineSolution");
    model.sol("sol4").feature("cms1").set("control", "cmbsol");
    model.sol("sol4").attach("std3");
    model.sol("sol4").runAll();

    model.study("std2").create("cmbsol2", "CombineSolution");
    model.study("std2").feature("cmbsol2").set("soloper", "remsol");
    model.study("std2").feature("cmbsol2").set("excludeorinclude", "include");
    model.study("std2").feature("cmbsol2").set("solnum", "manual");
    model.study("std2").feature("cmbsol2").set("manualsolnum", 401);
    model.study("std2").feature("cmbsol2").set("clearsrcsol", true);

    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1);

    model.sol("sol2").study("std2");
    model.sol("sol3").copySolution("sol5");

    model.result().dataset("dset3").set("solution", "none");

    model.sol().remove("sol5");
    model.sol("sol2").create("su2", "StoreSolution");
    model.sol("sol2").create("cms2", "CombineSolution");

    return model;
  }

  public static Model run2(Model model) {
    model.sol("sol2").feature("cms2").set("control", "cmbsol2");

    model.result().dataset("dset3").set("solution", "sol3");

    model.sol("sol2").feature("cms2").set("solnum", "manual");
    model.sol("sol2").feature("cms2").set("solvertype", "solnum");
    model.sol("sol2").copySolution("sol5");
    model.sol("sol2").runFrom("cms2");

    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset4");
    model.result().dataset("surf1").selection().all();
    model.result().dataset().create("edg1", "Edge3D");
    model.result().dataset("edg1").label("z=0 \u5904\u7684\u8fb9");
    model.result().dataset("edg1").set("data", "dset4");
    model.result().dataset("edg1").selection().set(5, 13, 18, 19);
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").label("z=0 \u5904\u7684\u622a\u9762");
    model.result().dataset("cpl1").set("data", "dset4");
    model.result().dataset("cpl1").set("quickplane", "xy");
    model.result().dataset().create("cpl2", "CutPlane");
    model.result().dataset("cpl2").label("x=3.69*H \u5904\u7684\u622a\u9762");
    model.result().dataset("cpl2").set("data", "dset4");
    model.result().dataset("cpl2").set("quickx", "3.69*H");
    model.result().setOnlyPlotWhenRequested(true);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").set("view", "new");
    model.result("pg1").run();
    model.result("pg1").label("\u5c0f\u4e18\u4e0a\u7684 Cp \u7b49\u503c\u7ebf");
    model.result("pg1").set("data", "dset4");
    model.result("pg1").set("legendactive", true);
    model.result("pg1").set("legendtrailingzeros", true);
    model.result("pg1").set("legendprecision", 5);
    model.result("pg1").create("con1", "Contour");
    model.result("pg1").feature("con1").set("expr", "p/(0.5*rho*U^2)");
    model.result("pg1").feature("con1").set("descractive", true);
    model.result("pg1").feature("con1").set("descr", "Cp");
    model.result("pg1").feature("con1").set("levelmethod", "levels");
    model.result("pg1").feature("con1")
         .set("levels", "3.08E-01 2.13E-01 1.19E-01 2.40E-02 -7.05E-02 -1.65E-01 -2.60E-01 -3.54E-01 -4.49E-01 -5.43E-01 -6.38E-01 -7.32E-01 -8.27E-01 -9.21E-01 -1.02E+00");
    model.result("pg1").feature("con1").set("contourtype", "filled");
    model.result("pg1").feature("con1").set("legendtype", "filled");
    model.result("pg1").create("con2", "Contour");
    model.result("pg1").feature("con2").set("expr", "p/(0.5*rho*U^2)");
    model.result("pg1").feature("con2").set("descractive", true);
    model.result("pg1").feature("con2").set("descr", "Cp");
    model.result("pg1").feature("con2").set("titletype", "none");
    model.result("pg1").feature("con2").set("levelmethod", "levels");
    model.result("pg1").feature("con2")
         .set("levels", "3.08E-01 2.13E-01 1.19E-01 2.40E-02 -7.05E-02 -1.65E-01 -2.60E-01 -3.54E-01 -4.49E-01 -5.43E-01 -6.38E-01 -7.32E-01 -8.27E-01 -9.21E-01 -1.02E+00");
    model.result("pg1").feature("con2").set("coloring", "uniform");
    model.result("pg1").feature("con2").set("color", "gray");
    model.result("pg1").feature("con2").set("colorlegend", false);
    model.result("pg1").run();

    model.view("view5").camera().set("projection", "orthographic");

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").set("view", "new");
    model.result("pg2").run();
    model.result("pg2").label("\u65e0\u91cf\u7eb2\u6da1\u91cf\u901a\u91cf");
    model.result("pg2").set("data", "dset4");
    model.result("pg2").set("edges", false);
    model.result("pg2").selection().geom("geom1", 2);
    model.result("pg2").selection().geom("geom1", 2);
    model.result("pg2").selection().set(6, 7, 8, 9);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").feature("surf1").set("titletype", "none");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1")
         .set("expr", new String[]{"(nz*dtang(p, y)-ny*dtang(p, z))/(0.5*rho*U^2)", "", ""});
    model.result("pg2").feature("arws1").setIndex("expr", "(nx*dtang(p, z)-nz*dtang(p, x))/(0.5*rho*U^2)", 1);
    model.result("pg2").feature("arws1").setIndex("expr", "(ny*dtang(p, x)-nx*dtang(p, y))/(0.5*rho*U^2)", 2);
    model.result("pg2").feature("arws1").set("plotcomp", "tangential");
    model.result("pg2").feature("arws1").set("titletype", "manual");
    model.result("pg2").feature("arws1")
         .set("title", "\u9762\u4e0a\u7bad\u5934\uff1a\u65e0\u91cf\u7eb2\u6da1\u91cf\u901a\u91cf");
    model.result("pg2").feature("arws1").set("arrowcount", 400);
    model.result("pg2").feature("arws1").set("scaleactive", true);
    model.result("pg2").feature("arws1").set("scale", 0.25);
    model.result("pg2").run();

    model.view("view6").camera().set("projection", "orthographic");

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").set("view", "new");
    model.result("pg3").run();
    model.result("pg3").label("z=0 \u5904\u7684\u901f\u5ea6\u77e2\u91cf");
    model.result("pg3").set("data", "none");
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("data", "cpl1");
    model.result("pg3").feature("arws1").setIndex("expr", "u", 0);
    model.result("pg3").feature("arws1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("arws1").set("plotcomp", "tangential");
    model.result("pg3").feature("arws1").set("titletype", "manual");
    model.result("pg3").feature("arws1")
         .set("title", "\u9762\u4e0a\u7bad\u5934\uff1az=0 \u5904\u7684\u901f\u5ea6\u573a");
    model.result("pg3").feature("arws1").set("placement", "elements");
    model.result("pg3").feature("arws1").set("scaleactive", true);
    model.result("pg3").feature("arws1").set("scale", 0.08);
    model.result("pg3").feature("arws1").create("filt1", "Filter");
    model.result("pg3").feature("arws1").feature("filt1").set("expr", "(x>=0) && (x<=2*H) && (y<= 1.2*H)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("data", "cpl1");
    model.result("pg3").feature("surf1").set("expr", "1");
    model.result("pg3").feature("surf1").set("titletype", "manual");
    model.result("pg3").feature("surf1").set("title", "\u6d0b\u7ea2\u8272\u7684\u56de\u6d41\u533a");
    model.result("pg3").feature("surf1").set("coloring", "uniform");
    model.result("pg3").feature("surf1").set("color", "magenta");
    model.result("pg3").feature("surf1").create("filt1", "Filter");
    model.result("pg3").feature("surf1").feature("filt1").set("expr", "(u<=0) && (x>=0) && (x<=2*H) && (y<= 1.2*H)");
    model.result("pg3").run();

    model.view("view7").camera().set("projection", "orthographic");

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").set("view", "new");
    model.result("pg4").run();
    model.result("pg4").label("x=3.69H \u5904\u7684\u901f\u5ea6\u77e2\u91cf");
    model.result("pg4").set("data", "none");
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("data", "cpl2");
    model.result("pg4").feature("arws1").setIndex("expr", "u", 0);
    model.result("pg4").feature("arws1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").feature("arws1").set("plotcomp", "tangential");
    model.result("pg4").feature("arws1").set("titletype", "manual");
    model.result("pg4").feature("arws1")
         .set("title", "\u9762\u4e0a\u7bad\u5934\uff1ax=3.69H \u5904\u7684\u901f\u5ea6\u573a");
    model.result("pg4").feature("arws1").set("arrowcount", "4e4");
    model.result("pg4").feature("arws1").set("scaleactive", true);
    model.result("pg4").feature("arws1").set("scale", 0.6);
    model.result("pg4").feature("arws1").create("filt1", "Filter");
    model.result("pg4").feature("arws1").feature("filt1").set("expr", "(y>=0) && (y<=H) && (z>=0) && (z<=2.5*H)");
    model.result("pg4").run();

    model.view("view8").camera().set("projection", "orthographic");

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").set("view", "new");
    model.result("pg5").run();
    model.result("pg5").label("\u77ac\u65f6\u6d41\u7ebf");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("legendpos", "bottom");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "1");
    model.result("pg5").feature("surf1").set("titletype", "none");
    model.result("pg5").feature("surf1").set("coloring", "uniform");
    model.result("pg5").feature("surf1").set("color", "white");
    model.result("pg5").feature("surf1").create("filt1", "Filter");
    model.result("pg5").feature("surf1").feature("filt1")
         .set("expr", "(x>=-4*H) && (x<=6*H) && (z>=-3*H) && (z<=3*H) && (y>=0) && (y<=1.5*H)");
    model.result("pg5").set("edges", false);
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("posmethod", "start");
    model.result("pg5").feature("str1").set("startmethod", "coord");
    model.result("pg5").feature("str1").set("xcoord", "-4*H");
    model.result("pg5").feature("str1").set("ycoord", "0.01*H");
    model.result("pg5").feature("str1").set("zcoord", "range(-2*H,4*H/10,2*H)");
    model.result("pg5").feature("str1").set("linetype", "tube");
    model.result("pg5").feature("str1").set("radiusexpr", "0.01*H");
    model.result("pg5").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg5").feature("str1").create("col1", "Color");
    model.result("pg5").feature("str1").create("filt1", "Filter");
    model.result("pg5").feature("str1").feature("filt1")
         .set("expr", "(x>=-4*H) && (x<=6*H) && (z>=-3*H) && (z<=3*H) && (y>=0) && (y<=1.5*H)");
    model.result("pg5").feature().duplicate("str2", "str1");
    model.result("pg5").feature("str2").set("titletype", "none");
    model.result("pg5").feature("str2").set("ycoord", "range(0,0.2*H,1.6*H)");
    model.result("pg5").feature("str2").set("zcoord", 0);
    model.result("pg5").feature("str2").set("inheritplot", "str1");
    model.result("pg5").feature().duplicate("str3", "str2");
    model.result("pg5").feature("str3").set("xcoord", "2*H");
    model.result("pg5").feature("str3").set("ycoord", "0.01*H");
    model.result("pg5").feature("str3").set("zcoord", "range(-2*H,4*H/10,2*H)");
    model.result("pg5").feature().duplicate("str4", "str3");
    model.result("pg5").feature("str4").set("ycoord", "0.1*H");
    model.result("pg5").feature().duplicate("str5", "str4");
    model.result("pg5").feature("str5").set("ycoord", "0.3*H");
    model.result("pg5").create("slc1", "Slice");
    model.result("pg5").feature("slc1").set("quickplane", "xy");
    model.result("pg5").feature("slc1").set("quickznumber", 1);
    model.result("pg5").feature("slc1").set("inheritplot", "str1");
    model.result("pg5").feature("slc1").create("trn1", "Translation");
    model.result("pg5").feature("slc1").feature("trn1").set("trans", new String[]{"0", "0", "-2.9*H"});
    model.result("pg5").feature("slc1").create("filt1", "Filter");
    model.result("pg5").feature("slc1").feature("filt1")
         .set("expr", "(x>=-4*H) && (x<=6*H) && (z>=-3*H) && (z<=3*H) && (y>=0) && (y<=1.5*H)");
    model.result("pg5").run();

    model.view("view9").camera().set("position", new double[]{-66.81538391113281, -4, 3});
    model.view("view9").camera().setIndex("position", 64.47892761230469, 1);
    model.view("view9").camera().setIndex("position", 75.17528533935547, 2);
    model.view("view9").camera().setIndex("target", 5.718170166015625, 0);
    model.view("view9").camera().setIndex("target", 1.5617790222167969, 1);
    model.view("view9").camera().setIndex("target", 0.06719970703125, 2);
    model.view("view9").camera().set("up", new double[]{0.6154333353042603, 0, 1});
    model.view("view9").camera().setIndex("up", 0.447165846824646, 1);
    model.view("view9").camera().setIndex("up", -0.6490663290023804, 2);
    model.view("view9").camera().set("rotationpoint", new double[]{1, 0.949999988079071, 0});
    model.view("view9").camera().set("viewoffset", new double[]{0.2787371873855591, 0});
    model.view("view9").camera().setIndex("viewoffset", 0.25127044320106506, 1);
    model.view("view9").camera().set("projection", "orthographic");

    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1);
    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1);
    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1);

    model.sol("sol1").study("std1");
    model.sol("sol1").feature().remove("s1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "stat");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "stat");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("s1").create("se1", "Segregated");
    model.sol("sol1").feature("s1").feature("se1").feature().remove("ssDef");
    model.sol("sol1").feature("s1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("segvar", new String[]{"comp1_phi"});
    model.sol("sol1").feature("s1").create("i1", "Iterative");
    model.sol("sol1").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol1").feature("s1").feature("i1").set("prefuntype", "left");
    model.sol("sol1").feature("s1").feature("i1").set("itrestart", 50);
    model.sol("sol1").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol1").feature("s1").feature("i1").set("maxlinit", 1000);
    model.sol("sol1").feature("s1").feature("i1").set("nlinnormuse", "on");
    model.sol("sol1").feature("s1").feature("i1").label("AMG\uff0c\u52bf\u6d41 (ipf)");
    model.sol("sol1").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("linsolver", "i1");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").label("\u901f\u5ea6\u52bf");
    model.sol("sol1").feature("s1").feature("se1").create("ss2", "SegregatedStep");
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("segvar", new String[]{"comp1_uPhi"});
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("subdamp", 1);
    model.sol("sol1").feature("s1").create("i2", "Iterative");
    model.sol("sol1").feature("s1").feature("i2").set("linsolver", "gmres");
    model.sol("sol1").feature("s1").feature("i2").set("prefuntype", "left");
    model.sol("sol1").feature("s1").feature("i2").set("itrestart", 50);
    model.sol("sol1").feature("s1").feature("i2").set("rhob", 400);
    model.sol("sol1").feature("s1").feature("i2").set("maxlinit", 1000);
    model.sol("sol1").feature("s1").feature("i2").set("nlinnormuse", "on");
    model.sol("sol1").feature("s1").feature("i2").label("AMG\uff0c\u52bf\u6d41\u7684\u5e73\u6ed1\u901f\u5ea6 (ipf)");
    model.sol("sol1").feature("s1").feature("i2").create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("linsolver", "i2");
    model.sol("sol1").feature("s1").feature("se1").feature("ss2")
         .label("\u52bf\u6d41\u7684\u5e73\u6ed1\u901f\u5ea6");
    model.sol("sol1").feature("s1").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("d1").label("\u76f4\u63a5\uff0c\u52bf\u6d41 (ipf)");
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol2").feature("v1").set("notsolnum", "auto");
    model.sol("sol2").feature("v1").set("notsolvertype", "solnum");
    model.sol("sol2").feature("v1").set("solnum", "auto");
    model.sol("sol2").feature("v1").set("solvertype", "solnum");

    model.study("std2").feature("time").set("notsolnum", "auto");
    model.study("std2").feature("time").set("notsolvertype", "solnum");
    model.study("std2").feature("time").set("notsolnumhide", "off");
    model.study("std2").feature("time").set("notstudyhide", "off");
    model.study("std2").feature("time").set("notsolhide", "on");
    model.study("std2").feature("time").set("solnum", "auto");
    model.study("std2").feature("time").set("solvertype", "solnum");
    model.study("std2").feature("time").set("solnumhide", "off");
    model.study("std2").feature("time").set("initstudyhide", "off");
    model.study("std2").feature("time").set("initsolhide", "on");

    model.sol("sol1").feature().move("st1", 0);
    model.sol("sol1").feature("st1").set("studystep", "stat");
    model.sol("sol1").feature().move("v1", 1);
    model.sol("sol1").feature("v1").set("control", "stat");
    model.sol("sol1").feature().move("s1", 2);
    model.sol("sol1").feature("s1").set("control", "stat");

    model.title("\u5c0f\u4e18\u72b6\u4e09\u7ef4\u51e0\u4f55\u7684\u5927\u6da1\u6a21\u62df");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u201c\u5927\u6da1\u6a21\u62df\u201d(LES) \u63a5\u53e3\u548c\u5165\u53e3\u8fb9\u754c\u5904\u7684\u5408\u6210\u6e4d\u6d41\u6765\u6a21\u62df\u5c0f\u4e18\u72b6\u4e09\u7ef4\u51e0\u4f55\u4e0a\u7684\u6e4d\u6d41\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("les_3d_hill.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
