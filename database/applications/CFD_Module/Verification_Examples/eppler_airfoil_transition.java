/*
 * eppler_airfoil_transition.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class eppler_airfoil_transition {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ipf", "IncompressiblePotentialFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ipf", true);

    model.param().set("U0", "1[m/s]");
    model.param().set("rho0", "1[kg/m^3]");
    model.param().set("mu0", "5e-6[Pa*s]");
    model.param().set("k0", "3/2*(0.18*U0)^2");
    model.param().set("muT0", "2*mu0");
    model.param().set("om0", "rho0*k0/mu0");
    model.param().set("alpha0", "0[deg]");
    model.param().set("chord", "1[m]");

    model.component("comp1").geom("geom1").create("ic1", "InterpolationCurve");
    model.component("comp1").geom("geom1").feature("ic1").set("type", "solid");
    model.component("comp1").geom("geom1").feature("ic1")
         .set("table", new String[][]{{"1.00000", "0.00000"}, 
         {"0.99677", "0.00043"}, 
         {"0.98729", "0.00180"}, 
         {"0.97198", "0.00423"}, 
         {"0.95128", "0.00763"}, 
         {"0.92554", "0.01184"}, 
         {"0.89510", "0.01679"}, 
         {"0.86035", "0.02242"}, 
         {"0.82183", "0.02866"}, 
         {"0.78007", "0.03540"}, 
         {"0.73567", "0.04249"}, 
         {"0.68922", "0.04975"}, 
         {"0.64136", "0.05696"}, 
         {"0.59272", "0.06390"}, 
         {"0.54394", "0.07020"}, 
         {"0.49549", "0.07546"}, 
         {"0.44767", "0.07936"}, 
         {"0.40077", "0.08173"}, 
         {"0.35505", "0.08247"}, 
         {"0.31078", "0.08156"}, 
         {"0.26813", "0.07908"}, 
         {"0.22742", "0.07529"}, 
         {"0.18906", "0.07037"}, 
         {"0.15345", "0.06448"}, 
         {"0.12094", "0.05775"}, 
         {"0.09185", "0.05033"}, 
         {"0.06643", "0.04238"}, 
         {"0.04493", "0.03408"}, 
         {"0.02748", "0.02562"}, 
         {"0.01423", "0.01726"}, 
         {"0.00519", "0.00931"}, 
         {"0.00044", "0.00234"}, 
         {"0.00091", "-0.00286"}, 
         {"0.00717", "-0.00682"}, 
         {"0.01890", "-0.01017"}, 
         {"0.03596", "-0.01265"}, 
         {"0.05827", "-0.01425"}, 
         {"0.08569", "-0.01500"}, 
         {"0.11800", "-0.01502"}, 
         {"0.15490", "-0.01441"}, 
         {"0.19599", "-0.01329"}, 
         {"0.24083", "-0.01177"}, 
         {"0.28892", "-0.00998"}, 
         {"0.33968", "-0.00804"}, 
         {"0.39252", "-0.00605"}, 
         {"0.44679", "-0.00410"}, 
         {"0.50182", "-0.00228"}, 
         {"0.55694", "-0.00065"}, 
         {"0.61147", "0.00074"}, 
         {"0.66472", "0.00186"}, 
         {"0.71602", "0.00268"}, 
         {"0.76475", "0.00320"}, 
         {"0.81027", "0.00342"}, 
         {"0.85202", "0.00337"}, 
         {"0.88944", "0.00307"}, 
         {"0.92205", "0.00258"}, 
         {"0.94942", "0.00196"}, 
         {"0.97118", "0.00132"}, 
         {"0.98705", "0.00071"}, 
         {"0.99674", "0.00021"}, 
         {"1.00000", "0.00000"}});
    model.component("comp1").geom("geom1").run("ic1");
    model.component("comp1").geom("geom1").create("off1", "Offset");
    model.component("comp1").geom("geom1").feature("off1").selection("input").set("ic1");
    model.component("comp1").geom("geom1").feature("off1").set("distance", 0.25);
    model.component("comp1").geom("geom1").run("off1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 100);
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new int[]{1, 0});
    model.component("comp1").geom("geom1").feature("c1").set("rot", 90);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{100, 200});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{1, -100});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new double[]{-0.25, 0});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new double[]{0.005, 0});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("c1", "ls1", "off1", "r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("ic1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("igv1", "IgnoreVertices");
    model.component("comp1").geom("geom1").feature("igv1").selection("input").set("fin", 3, 5, 6, 12);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(13, 14);

    model.component("comp1").variable().create("var1");

    model.component("comp1").physics("ipf").prop("PressureProperty").set("UScale", "U0");
    model.component("comp1").physics("ipf").feature("fp1").set("rho_mat", "userdef");
    model.component("comp1").physics("ipf").feature("fp1").set("rho", "rho0");
    model.component("comp1").physics("ipf").create("velbc1", "Velocity", 1);
    model.component("comp1").physics("ipf").feature("velbc1").selection().set(3, 7, 9, 10);
    model.component("comp1").physics("ipf").feature("velbc1")
         .set("Uin", "U0*(nxmesh*cos(alpha0)+nymesh*sin(alpha0))");
    model.component("comp1").physics("ipf").create("open1", "OpenBoundary", 1);
    model.component("comp1").physics("ipf").feature("open1").selection().set(8);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 5);
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.2);
    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature().move("fq1", 1);
    model.component("comp1").mesh("mesh1").feature("fq1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("fq1").selection().set(1, 2, 5);
    model.component("comp1").mesh("mesh1").feature("fq1").set("smoothmaxiter", 10);
    model.component("comp1").mesh("mesh1").feature("fq1").set("smoothmaxdepth", 8);
    model.component("comp1").mesh("mesh1").feature("fq1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmax", 0.005);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmin", 0.001);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(1, 2, 3, 5);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(13, 14);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 60);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.075);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "1e-5");
    model.component("comp1").mesh("mesh1").run();

    model.sol().create("sol1");

    model.component("comp1").mesh("mesh1").stat().selection().geom(2);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2, 3, 4, 5);

    model.sol("sol1").study("std1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "stat");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "stat");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("d1").label("\u76f4\u63a5\uff0c\u52bf\u6d41 (ipf)");
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
    model.sol("sol1").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u57fa\u4e8e\u52bf\u6d41\u89e3\u7684\u901f\u5ea6 (ipf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("data", "dset1");
    model.result("pg1")
         .set("defaultPlotID", "potential_flow/IncompressiblePotentialFlow_ResultDefaults/icom1/pdef1/pcond1/pg1");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();

    model.component("comp1").physics().create("spf", "TurbulentFlowSST", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

    model.component("comp1").physics("spf").prop("TurbulenceModelProperty")
         .set("WallTreatment", "LowReynoldsNumber");
    model.component("comp1").physics("spf").feature("fp1").set("rho_mat", "userdef");
    model.component("comp1").physics("spf").feature("fp1").set("rho", "rho0");
    model.component("comp1").physics("spf").feature("fp1").set("mu_mat", "userdef");
    model.component("comp1").physics("spf").feature("fp1").set("mu", "mu0");
    model.component("comp1").physics("spf").feature("fp1").set("LengthScaleSpecification", "Manual");
    model.component("comp1").physics("spf").feature("fp1").set("lref", 0.2);
    model.component("comp1").physics("spf").feature("init1").set("u_init", new String[]{"ipf.u", "ipf.v", "0"});
    model.component("comp1").physics("spf").feature("init1").set("p_init", "ipf.p");
    model.component("comp1").physics("spf").feature("init1").set("k_init", "k0");
    model.component("comp1").physics("spf").feature("init1").set("om_init", "om0");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(3, 7, 9, 10);
    model.component("comp1").physics("spf").feature("inl1").set("ComponentWise", "VelocityFieldComponentWise");
    model.component("comp1").physics("spf").feature("inl1")
         .set("u0", new String[]{"U0*cos(alpha0)", "U0*sin(alpha0)", "0"});
    model.component("comp1").physics("spf").feature("inl1").set("RANSVarOption", "SpecifyTurbulenceVariables");
    model.component("comp1").physics("spf").feature("inl1").set("k0", "k0");
    model.component("comp1").physics("spf").feature("inl1").set("om0", "om0");
    model.component("comp1").physics("spf").create("open1", "OpenBoundary", 1);
    model.component("comp1").physics("spf").feature("open1").selection().set(8);
    model.component("comp1").physics("spf").feature("open1").set("RANSVarOption", "SpecifyTurbulenceVariables");
    model.component("comp1").physics("spf").feature("open1").set("k0", "k0");
    model.component("comp1").physics("spf").feature("open1").set("om0", "om0");

    model.study().create("std2");
    model.study("std2").create("wdi", "WallDistanceInitialization");
    model.study("std2").feature("wdi").set("plotgroup", "Default");
    model.study("std2").feature("wdi").set("solnum", "auto");
    model.study("std2").feature("wdi").set("notsolnum", "auto");
    model.study("std2").feature("wdi").set("outputmap", new String[]{});
    model.study("std2").feature("wdi").set("ngenAUX", "1");
    model.study("std2").feature("wdi").set("goalngenAUX", "1");
    model.study("std2").feature("wdi").set("ngenAUX", "1");
    model.study("std2").feature("wdi").set("goalngenAUX", "1");
    model.study("std2").feature("wdi").setSolveFor("/physics/ipf", true);
    model.study("std2").feature("wdi").setSolveFor("/physics/spf", true);
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").set("plotgroup", "Default");
    model.study("std2").feature("stat").set("solnum", "auto");
    model.study("std2").feature("stat").set("notsolnum", "auto");
    model.study("std2").feature("stat").set("outputmap", new String[]{});
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").setSolveFor("/physics/ipf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature("stat").setEntry("activate", "ipf", false);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "U0", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m/s", 0);
    model.study("std2").feature("stat").setIndex("pname", "U0", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m/s", 0);
    model.study("std2").feature("stat").setIndex("pname", "alpha0", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "0,2,4,6", 0);
    model.study("std2").feature("stat").setIndex("punit", "deg", 0);
    model.study("std2").feature("wdi").set("usesol", true);
    model.study("std2").feature("wdi").set("notstudy", "std1");

    model.sol().create("sol2");

    model.component("comp1").mesh("mesh1").stat().selection().geom(2);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2, 3, 4, 5);
    model.component("comp1").mesh("mesh1").stat().selection().geom(2);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2, 3, 4, 5);
    model.component("comp1").mesh("mesh1").stat().selection().geom(2);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2, 3, 4, 5);

    model.sol("sol2").study("std2");
    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "wdi");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").set("control", "wdi");
    model.sol("sol2").create("s1", "Stationary");
    model.sol("sol2").feature("s1").set("stol", 1.0E-6);
    model.sol("sol2").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("s1").feature("fc1").set("dtech", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("initstep", 0.01);
    model.sol("sol2").feature("s1").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol2").feature("s1").feature("fc1").set("maxiter", 50);
    model.sol("sol2").feature("s1").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol2").feature("s1").feature("d1").label("\u76f4\u63a5\uff0c\u58c1\u8ddd\u79bb (spf)");
    model.sol("sol2").feature("s1").create("i1", "Iterative");
    model.sol("sol2").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol2").feature("s1").feature("i1").set("prefuntype", "left");
    model.sol("sol2").feature("s1").feature("i1").set("itrestart", 50);
    model.sol("sol2").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol2").feature("s1").feature("i1").set("maxlinit", 1000);
    model.sol("sol2").feature("s1").feature("i1").set("nlinnormuse", "on");
    model.sol("sol2").feature("s1").feature("i1").label("AMG\uff0c\u58c1\u8ddd\u79bb (spf)");
    model.sol("sol2").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("strconn", 0.01);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("compactaggregation", false);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("iter", 1);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("linerelax", 0.7);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linemethod", "uncoupled");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("seconditer", 1);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("relax", 0.5);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("iter", 1);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("linerelax", 0.7);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linemethod", "uncoupled");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("seconditer", 1);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("relax", 0.5);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol2").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol2").feature("s1").feature("fc1").set("dtech", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("initstep", 0.01);
    model.sol("sol2").feature("s1").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol2").feature("s1").feature("fc1").set("maxiter", 50);
    model.sol("sol2").feature("s1").feature().remove("fcDef");
    model.sol("sol2").create("su1", "StoreSolution");
    model.sol("sol2").create("st2", "StudyStep");
    model.sol("sol2").feature("st2").set("study", "std2");
    model.sol("sol2").feature("st2").set("studystep", "stat");
    model.sol("sol2").create("v2", "Variables");
    model.sol("sol2").feature("v2").set("initmethod", "sol");
    model.sol("sol2").feature("v2").set("initsol", "sol2");
    model.sol("sol2").feature("v2").set("notsolmethod", "sol");
    model.sol("sol2").feature("v2").set("notsol", "sol2");
    model.sol("sol2").feature("v2").set("control", "stat");
    model.sol("sol2").create("s2", "Stationary");
    model.sol("sol2").feature("s2").create("p1", "Parametric");
    model.sol("sol2").feature("s2").feature().remove("pDef");
    model.sol("sol2").feature("s2").feature("p1").set("control", "stat");
    model.sol("sol2").feature("s2").set("control", "stat");
    model.sol("sol2").feature("s2").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("s2").create("se1", "Segregated");
    model.sol("sol2").feature("s2").feature("se1").feature().remove("ssDef");
    model.sol("sol2").feature("s2").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol2").feature("s2").feature("se1").feature("ss1").set("segvar", new String[]{"comp1_p", "comp1_u"});
    model.sol("sol2").feature("s2").feature("se1").feature("ss1").set("subdamp", 0.5);
    model.sol("sol2").feature("s2").create("d1", "Direct");
    model.sol("sol2").feature("s2").feature("d1").set("linsolver", "pardiso");
    model.sol("sol2").feature("s2").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol2").feature("s2").feature("d1")
         .label("\u76f4\u63a5\uff0c\u6d41\u4f53\u6d41\u52a8\u53d8\u91cf (spf)");
    model.sol("sol2").feature("s2").feature("se1").feature("ss1").set("linsolver", "d1");
    model.sol("sol2").feature("s2").feature("se1").feature("ss1").label("\u901f\u5ea6 u\uff0c\u538b\u529b p");
    model.sol("sol2").feature("s2").feature("se1").create("ss2", "SegregatedStep");
    model.sol("sol2").feature("s2").feature("se1").feature("ss2").set("segvar", new String[]{"comp1_k", "comp1_om"});
    model.sol("sol2").feature("s2").feature("se1").feature("ss2").set("subdamp", 0.45);
    model.sol("sol2").feature("s2").feature("se1").feature("ss2").set("subiter", 3);
    model.sol("sol2").feature("s2").feature("se1").feature("ss2").set("subtermconst", "itertol");
    model.sol("sol2").feature("s2").feature("se1").feature("ss2").set("subntolfact", 1);
    model.sol("sol2").feature("s2").create("d2", "Direct");
    model.sol("sol2").feature("s2").feature("d2").set("linsolver", "pardiso");
    model.sol("sol2").feature("s2").feature("d2").set("pivotperturb", 1.0E-13);
    model.sol("sol2").feature("s2").feature("d2").label("\u76f4\u63a5\uff0c\u6e4d\u6d41\u53d8\u91cf (spf)");
    model.sol("sol2").feature("s2").feature("se1").feature("ss2").set("linsolver", "d2");
    model.sol("sol2").feature("s2").feature("se1").feature("ss2").label("\u6e4d\u6d41\u53d8\u91cf");
    model.sol("sol2").feature("s2").feature("se1").set("segstabacc", "segcflcmp");
    model.sol("sol2").feature("s2").feature("se1").set("subinitcfl", 2);
    model.sol("sol2").feature("s2").feature("se1").set("submincfl", 10000);
    model.sol("sol2").feature("s2").feature("se1").set("subkppid", 0.65);
    model.sol("sol2").feature("s2").feature("se1").set("subkdpid", 0.05);
    model.sol("sol2").feature("s2").feature("se1").set("subkipid", 0.05);
    model.sol("sol2").feature("s2").feature("se1").set("subcfltol", 0.1);
    model.sol("sol2").feature("s2").feature("se1").set("segcflaa", true);
    model.sol("sol2").feature("s2").feature("se1").set("segcflaacfl", 9000);
    model.sol("sol2").feature("s2").feature("se1").set("segcflaafact", 1);
    model.sol("sol2").feature("s2").feature("se1").set("maxsegiter", 300);
    model.sol("sol2").feature("s2").feature("se1").create("ll1", "LowerLimit");
    model.sol("sol2").feature("s2").feature("se1").feature("ll1").set("lowerlimit", "comp1.k 0 comp1.om 0 ");
    model.sol("sol2").feature("s2").create("i1", "Iterative");
    model.sol("sol2").feature("s2").feature("i1").set("linsolver", "gmres");
    model.sol("sol2").feature("s2").feature("i1").set("prefuntype", "left");
    model.sol("sol2").feature("s2").feature("i1").set("itrestart", 50);
    model.sol("sol2").feature("s2").feature("i1").set("rhob", 20);
    model.sol("sol2").feature("s2").feature("i1").set("maxlinit", 1000);
    model.sol("sol2").feature("s2").feature("i1").set("nlinnormuse", "on");
    model.sol("sol2").feature("s2").feature("i1").label("AMG\uff0c\u6d41\u4f53\u6d41\u52a8\u53d8\u91cf (spf)");
    model.sol("sol2").feature("s2").feature("i1").create("mg1", "Multigrid");
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").set("maxcoarsedof", 80000);
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").set("strconn", 0.02);
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").set("compactaggregation", false);
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").feature("pr").create("sc1", "SCGS");
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1").set("iter", 0);
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1").set("scgsrelax", 0.7);
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsmethod", "lines");
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1").set("relax", 0.5);
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgssolv", "stored");
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").feature("po").create("sc1", "SCGS");
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1").set("iter", 1);
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1").set("scgsrelax", 0.7);
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsmethod", "lines");
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1").set("relax", 0.5);
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgssolv", "stored");
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsdirectmaxsize", 1000);

    return model;
  }

  public static Model run2(Model model) {
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol2").feature("s2").create("i2", "Iterative");
    model.sol("sol2").feature("s2").feature("i2").set("linsolver", "gmres");
    model.sol("sol2").feature("s2").feature("i2").set("prefuntype", "left");
    model.sol("sol2").feature("s2").feature("i2").set("itrestart", 50);
    model.sol("sol2").feature("s2").feature("i2").set("rhob", 20);
    model.sol("sol2").feature("s2").feature("i2").set("maxlinit", 1000);
    model.sol("sol2").feature("s2").feature("i2").set("nlinnormuse", "on");
    model.sol("sol2").feature("s2").feature("i2").label("AMG\uff0c\u6e4d\u6d41\u53d8\u91cf (spf)");
    model.sol("sol2").feature("s2").feature("i2").create("mg1", "Multigrid");
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").set("prefun", "saamg");
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").set("mgcycle", "v");
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").set("strconn", 0.01);
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").set("nullspace", "constant");
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").set("usesmooth", false);
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").set("saamgcompwise", true);
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").set("loweramg", true);
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").set("compactaggregation", false);
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").feature("pr").feature("sl1").set("iter", 0);
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").feature("pr").feature("sl1").set("linerelax", 0.7);
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linemethod", "uncoupled");
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").feature("pr").feature("sl1").set("seconditer", 1);
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").feature("pr").feature("sl1").set("relax", 0.5);
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").feature("po").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").feature("po").feature("sl1").set("iter", 1);
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").feature("po").feature("sl1").set("linerelax", 0.7);
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").feature("po").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").feature("po").feature("sl1")
         .set("linemethod", "uncoupled");
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").feature("po").feature("sl1").set("seconditer", 1);
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").feature("po").feature("sl1").set("relax", 0.5);
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol2").feature("s2").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol2").feature("s2").feature().remove("fcDef");
    model.sol("sol2").feature("v2").set("notsolnum", "auto");
    model.sol("sol2").feature("v2").set("notsolvertype", "solnum");
    model.sol("sol2").feature("v2").set("solnum", "auto");
    model.sol("sol2").feature("v2").set("solvertype", "solnum");
    model.sol("sol2").attach("std2");
    model.sol("sol2").feature("s2").feature("se1").set("maxsegiter", 400);
    model.sol("sol2").feature("s2").feature("se1").set("subkdpid", 0.03);
    model.sol("sol2").feature("s2").feature("se1").set("subcfltol", 0.08);
    model.sol("sol2").feature("s2").feature("se1").set("segcflaa", false);
    model.sol("sol2").runAll();

    model.result().dataset("dset2").set("geom", "geom1");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u901f\u5ea6 (spf)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 4, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 4, 0);
    model.result("pg2").set("defaultPlotID", "ResultDefaults_SinglePhaseFlow/icom1/pdef1/pcond1/pg1");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "spf.U");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u538b\u529b (spf)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 4, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 4, 0);
    model.result("pg3").set("defaultPlotID", "ResultDefaults_SinglePhaseFlow/icom1/pdef1/pcond1/pg2");
    model.result("pg3").feature().create("con1", "Contour");
    model.result("pg3").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("expr", "p");
    model.result("pg3").feature("con1").set("number", 40);
    model.result("pg3").feature("con1").set("levelrounding", false);
    model.result("pg3").feature("con1").set("smooth", "internal");
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("data", "parent");
    model.result().dataset().create("edg1", "Edge2D");
    model.result().dataset("edg1").label("\u5916\u58c1");
    model.result().dataset("edg1").set("data", "dset2");
    model.result().dataset("edg1").selection().geom("geom1", 1);
    model.result().dataset("edg1").selection().set(13, 14);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 4, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 4, 0);
    model.result("pg4").set("defaultPlotID", "ResultDefaults_SinglePhaseFlow/icom1/pdef1/pcond1/pcond2/pcond4/pg2");
    model.result("pg4").feature().create("line1", "Line");
    model.result("pg4").feature("line1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg4").feature("line1").set("showsolutionparams", "on");
    model.result("pg4").feature("line1").set("expr", "spf.Delta_wPlus");
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("smooth", "internal");
    model.result("pg4").feature("line1").set("showsolutionparams", "on");
    model.result("pg4").feature("line1").set("data", "parent");
    model.result("pg4").feature("line1").feature().create("hght1", "Height");
    model.result("pg4").feature("line1").feature("hght1").label("\u9ad8\u5ea6\u8868\u8fbe\u5f0f");
    model.result("pg4").feature("line1").feature("hght1").set("heightdata", "expr");
    model.result("pg4").feature("line1").feature("hght1").set("expr", "spf.WRHeightExpr");
    model.result("pg2").run();

    model.component("comp1").physics().create("spf2", "TurbulentFlowSST", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/spf2", true);
    model.study("std2").feature("wdi").setSolveFor("/physics/spf2", true);
    model.study("std2").feature("stat").setSolveFor("/physics/spf2", true);

    model.component("comp1").physics("spf2").prop("TurbulenceModelProperty").set("includeTransitionModeling", true);
    model.component("comp1").physics("spf2").feature("fp1").set("rho_mat", "userdef");
    model.component("comp1").physics("spf2").feature("fp1").set("rho", "rho0");
    model.component("comp1").physics("spf2").feature("fp1").set("mu_mat", "userdef");
    model.component("comp1").physics("spf2").feature("fp1").set("mu", "mu0");
    model.component("comp1").physics("spf2").feature("fp1").set("LengthScaleSpecification", "Manual");
    model.component("comp1").physics("spf2").feature("fp1").set("lref", 0.2);
    model.component("comp1").physics("spf2").feature("init1").set("u_init", new String[]{"u", "v", "0"});
    model.component("comp1").physics("spf2").feature("init1").set("p_init", "p");
    model.component("comp1").physics("spf2").feature("init1").set("k_init", "k");
    model.component("comp1").physics("spf2").feature("init1").set("om_init", "spf.om_global");
    model.component("comp1").physics("spf2").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf2").feature("inl1").selection().set(3, 7, 9, 10);
    model.component("comp1").physics("spf2").feature("inl1").set("ComponentWise", "VelocityFieldComponentWise");
    model.component("comp1").physics("spf2").feature("inl1")
         .set("u0", new String[]{"U0*cos(alpha0)", "U0*sin(alpha0)", "0"});
    model.component("comp1").physics("spf2").feature("inl1").set("RANSVarOption", "SpecifyTurbulenceVariables");
    model.component("comp1").physics("spf2").feature("inl1").set("k0", "k0");
    model.component("comp1").physics("spf2").feature("inl1").set("om0", "om0");
    model.component("comp1").physics("spf2").create("open1", "OpenBoundary", 1);
    model.component("comp1").physics("spf2").feature("open1").selection().set(8);
    model.component("comp1").physics("spf2").feature("open1").set("RANSVarOption", "SpecifyTurbulenceVariables");
    model.component("comp1").physics("spf2").feature("open1").set("k0", "k0");
    model.component("comp1").physics("spf2").feature("open1").set("om0", "om0");
    model.component("comp1").physics("spf2").feature("open1").set("gamma0", 1);

    model.study().create("std3");
    model.study("std3").create("wdi", "WallDistanceInitialization");
    model.study("std3").feature("wdi").set("plotgroup", "Default");
    model.study("std3").feature("wdi").set("solnum", "auto");
    model.study("std3").feature("wdi").set("notsolnum", "auto");
    model.study("std3").feature("wdi").set("outputmap", new String[]{});
    model.study("std3").feature("wdi").set("ngenAUX", "1");
    model.study("std3").feature("wdi").set("goalngenAUX", "1");
    model.study("std3").feature("wdi").set("ngenAUX", "1");
    model.study("std3").feature("wdi").set("goalngenAUX", "1");
    model.study("std3").feature("wdi").setSolveFor("/physics/ipf", true);
    model.study("std3").feature("wdi").setSolveFor("/physics/spf", true);
    model.study("std3").feature("wdi").setSolveFor("/physics/spf2", true);
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").set("plotgroup", "Default");
    model.study("std3").feature("time").set("initialtime", "0");
    model.study("std3").feature("time").set("solnum", "auto");
    model.study("std3").feature("time").set("notsolnum", "auto");
    model.study("std3").feature("time").set("outputmap", new String[]{});
    model.study("std3").feature("time").setSolveFor("/physics/ipf", true);
    model.study("std3").feature("time").setSolveFor("/physics/spf", true);
    model.study("std3").feature("time").setSolveFor("/physics/spf2", true);
    model.study("std3").create("param", "Parametric");
    model.study("std3").feature("param").setIndex("pname", "U0", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "m/s", 0);
    model.study("std3").feature("param").setIndex("pname", "U0", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "m/s", 0);
    model.study("std3").feature("param").setIndex("pname", "alpha0", 0);
    model.study("std3").feature("param").setIndex("plistarr", "0,2,4,6", 0);
    model.study("std3").feature("param").setIndex("punit", "deg", 0);
    model.study("std3").feature("wdi").setEntry("activate", "spf", false);
    model.study("std3").feature("wdi").set("usesol", true);
    model.study("std3").feature("wdi").set("notstudy", "std2");
    model.study("std3").feature("wdi").set("notsol", "sol2");
    model.study("std3").feature("time").set("tlist", "range(0,1,4), range(4,0.1,8)");
    model.study("std3").feature("time").setEntry("activate", "spf", false);

    model.sol().create("sol4");

    model.component("comp1").mesh("mesh1").stat().selection().geom(2);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2, 3, 4, 5);
    model.component("comp1").mesh("mesh1").stat().selection().geom(2);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2, 3, 4, 5);
    model.component("comp1").mesh("mesh1").stat().selection().geom(2);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2, 3, 4, 5);
    model.component("comp1").mesh("mesh1").stat().selection().geom(2);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2, 3, 4, 5);

    model.sol("sol4").study("std3");
    model.sol("sol4").create("st1", "StudyStep");
    model.sol("sol4").feature("st1").set("study", "std3");
    model.sol("sol4").feature("st1").set("studystep", "wdi");
    model.sol("sol4").create("v1", "Variables");
    model.sol("sol4").feature("v1").set("control", "wdi");
    model.sol("sol4").create("s1", "Stationary");
    model.sol("sol4").feature("s1").set("stol", 1.0E-6);
    model.sol("sol4").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol4").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol4").feature("s1").feature("fc1").set("dtech", "auto");
    model.sol("sol4").feature("s1").feature("fc1").set("initstep", 0.01);
    model.sol("sol4").feature("s1").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol4").feature("s1").feature("fc1").set("maxiter", 50);
    model.sol("sol4").feature("s1").create("d1", "Direct");
    model.sol("sol4").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol4").feature("s1").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol4").feature("s1").feature("d1").label("\u76f4\u63a5\uff0c\u58c1\u8ddd\u79bb (spf2)");
    model.sol("sol4").feature("s1").create("i1", "Iterative");
    model.sol("sol4").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol4").feature("s1").feature("i1").set("prefuntype", "left");
    model.sol("sol4").feature("s1").feature("i1").set("itrestart", 50);
    model.sol("sol4").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol4").feature("s1").feature("i1").set("maxlinit", 1000);
    model.sol("sol4").feature("s1").feature("i1").set("nlinnormuse", "on");
    model.sol("sol4").feature("s1").feature("i1").label("AMG\uff0c\u58c1\u8ddd\u79bb (spf2)");
    model.sol("sol4").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").set("strconn", 0.01);
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").set("compactaggregation", false);
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("iter", 1);
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("linerelax", 0.7);
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linemethod", "uncoupled");
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("seconditer", 1);
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("relax", 0.5);
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("iter", 1);
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("linerelax", 0.7);
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linemethod", "uncoupled");
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("seconditer", 1);
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("relax", 0.5);
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol4").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol4").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol4").feature("s1").feature("fc1").set("dtech", "auto");
    model.sol("sol4").feature("s1").feature("fc1").set("initstep", 0.01);
    model.sol("sol4").feature("s1").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol4").feature("s1").feature("fc1").set("maxiter", 50);
    model.sol("sol4").feature("s1").feature().remove("fcDef");
    model.sol("sol4").create("su1", "StoreSolution");
    model.sol("sol4").create("st2", "StudyStep");
    model.sol("sol4").feature("st2").set("study", "std3");
    model.sol("sol4").feature("st2").set("studystep", "time");
    model.sol("sol4").create("v2", "Variables");
    model.sol("sol4").feature("v2").set("initmethod", "sol");
    model.sol("sol4").feature("v2").set("initsol", "sol4");
    model.sol("sol4").feature("v2").set("initsoluse", "sol5");
    model.sol("sol4").feature("v2").set("notsolmethod", "sol");
    model.sol("sol4").feature("v2").set("notsol", "sol4");
    model.sol("sol4").feature("v2").set("notsoluse", "sol5");
    model.sol("sol4").feature("v2").set("control", "time");
    model.sol("sol4").create("t1", "Time");
    model.sol("sol4").feature("t1").set("tlist", "range(0,1,4), range(4,0.1,8)");
    model.sol("sol4").feature("t1").set("plot", "off");
    model.sol("sol4").feature("t1").set("plotgroup", "Default");
    model.sol("sol4").feature("t1").set("plotfreq", "tout");
    model.sol("sol4").feature("t1").set("probesel", "all");
    model.sol("sol4").feature("t1").set("probes", new String[]{});
    model.sol("sol4").feature("t1").set("probefreq", "tsteps");
    model.sol("sol4").feature("t1").set("rtol", 0.005);
    model.sol("sol4").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol4").feature("t1").set("atolglobalfactor", 0.05);
    model.sol("sol4").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol4").feature("t1")
         .set("atolmethod", new String[]{"comp1_G", "global", "comp1_G2", "global", "comp1_gamma2", "global", "comp1_k", "global", "comp1_k2", "unscaled", 
         "comp1_om", "global", "comp1_om2", "unscaled", "comp1_p", "global", "comp1_p2", "scaled", "comp1_phi", "global", 
         "comp1_u", "global", "comp1_u2", "global"});
    model.sol("sol4").feature("t1")
         .set("atol", new String[]{"comp1_G", "1e-3", "comp1_G2", "1e-3", "comp1_gamma2", "1e-3", "comp1_k", "1e-3", "comp1_k2", "(0.01*1)^2", 
         "comp1_om", "1e-3", "comp1_om2", "(0.01*1)/(0.035*200.0)", "comp1_p", "1e-3", "comp1_p2", "1e-3", "comp1_phi", "1e-3", 
         "comp1_u", "1e-3", "comp1_u2", "1e-3"});
    model.sol("sol4").feature("t1")
         .set("atolvaluemethod", new String[]{"comp1_G", "factor", "comp1_G2", "factor", "comp1_gamma2", "factor", "comp1_k", "factor", "comp1_k2", "manual", 
         "comp1_om", "factor", "comp1_om2", "manual", "comp1_p", "factor", "comp1_p2", "factor", "comp1_phi", "factor", 
         "comp1_u", "factor", "comp1_u2", "factor"});
    model.sol("sol4").feature("t1")
         .set("atolfactor", new String[]{"comp1_G", "0.1", "comp1_G2", "0.1", "comp1_gamma2", "0.1", "comp1_k", "0.1", "comp1_k2", "0.1", 
         "comp1_om", "0.1", "comp1_om2", "0.1", "comp1_p", "0.1", "comp1_p2", "1", "comp1_phi", "0.1", 
         "comp1_u", "0.1", "comp1_u2", "0.1"});
    model.sol("sol4").feature("t1").set("reacf", true);
    model.sol("sol4").feature("t1").set("storeudot", true);
    model.sol("sol4").feature("t1").set("endtimeinterpolation", true);
    model.sol("sol4").feature("t1").set("estrat", "exclude");
    model.sol("sol4").feature("t1").set("rhoinf", 0.5);
    model.sol("sol4").feature("t1").set("predictor", "constant");
    model.sol("sol4").feature("t1").set("maxorder", 2);
    model.sol("sol4").feature("t1").set("stabcntrl", true);
    model.sol("sol4").feature("t1").set("bwinitstepfrac", "0.01");
    model.sol("sol4").feature("t1").set("control", "time");
    model.sol("sol4").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol4").feature("t1").create("se1", "Segregated");
    model.sol("sol4").feature("t1").feature("se1").feature().remove("ssDef");
    model.sol("sol4").feature("t1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol4").feature("t1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_u2", "comp1_p2"});
    model.sol("sol4").feature("t1").feature("se1").feature("ss1").set("subdamp", 0.8);
    model.sol("sol4").feature("t1").feature("se1").feature("ss1").set("subjtech", "once");
    model.sol("sol4").feature("t1").create("d1", "Direct");
    model.sol("sol4").feature("t1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol4").feature("t1").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol4").feature("t1").feature("d1")
         .label("\u76f4\u63a5\uff0c\u6d41\u4f53\u6d41\u52a8\u53d8\u91cf (spf2)");
    model.sol("sol4").feature("t1").feature("se1").feature("ss1").set("linsolver", "d1");
    model.sol("sol4").feature("t1").feature("se1").feature("ss1").label("\u901f\u5ea6 u2\uff0c\u538b\u529b p2");
    model.sol("sol4").feature("t1").feature("se1").create("ss2", "SegregatedStep");
    model.sol("sol4").feature("t1").feature("se1").feature("ss2")
         .set("segvar", new String[]{"comp1_k2", "comp1_om2", "comp1_gamma2"});
    model.sol("sol4").feature("t1").feature("se1").feature("ss2").set("subdamp", 0.8);
    model.sol("sol4").feature("t1").feature("se1").feature("ss2").set("subiter", 1);
    model.sol("sol4").feature("t1").feature("se1").feature("ss2").set("subtermconst", "iter");
    model.sol("sol4").feature("t1").feature("se1").feature("ss2").set("subjtech", "onevery");
    model.sol("sol4").feature("t1").feature("se1").feature("ss2").set("subntolfact", 0.1);
    model.sol("sol4").feature("t1").create("d2", "Direct");
    model.sol("sol4").feature("t1").feature("d2").set("linsolver", "pardiso");
    model.sol("sol4").feature("t1").feature("d2").set("pivotperturb", 1.0E-13);
    model.sol("sol4").feature("t1").feature("d2").label("\u76f4\u63a5\uff0c\u6e4d\u6d41\u53d8\u91cf (spf2)");
    model.sol("sol4").feature("t1").feature("se1").feature("ss2").set("linsolver", "d2");
    model.sol("sol4").feature("t1").feature("se1").feature("ss2").label("\u6e4d\u6d41\u53d8\u91cf");
    model.sol("sol4").feature("t1").feature("se1").set("ntolfact", 0.5);
    model.sol("sol4").feature("t1").feature("se1").set("segstabacc", "segaacc");
    model.sol("sol4").feature("t1").feature("se1").set("segaaccdim", 5);
    model.sol("sol4").feature("t1").feature("se1").set("segaaccmix", 0.9);
    model.sol("sol4").feature("t1").feature("se1").set("segaaccdelay", 1);
    model.sol("sol4").feature("t1").feature("se1").set("maxsegiter", 10);
    model.sol("sol4").feature("t1").feature("se1").create("ll1", "LowerLimit");
    model.sol("sol4").feature("t1").feature("se1").feature("ll1")
         .set("lowerlimit", "comp1.k2 0 comp1.om2 0 comp1.gamma2 -1 ");
    model.sol("sol4").feature("t1").feature("se1").create("ul1", "UpperLimit");
    model.sol("sol4").feature("t1").feature("se1").feature("ul1").set("upperlimit", "comp1.gamma2 2 ");
    model.sol("sol4").feature("t1").create("i1", "Iterative");
    model.sol("sol4").feature("t1").feature("i1").set("linsolver", "gmres");
    model.sol("sol4").feature("t1").feature("i1").set("prefuntype", "left");
    model.sol("sol4").feature("t1").feature("i1").set("itrestart", 50);
    model.sol("sol4").feature("t1").feature("i1").set("rhob", 20);
    model.sol("sol4").feature("t1").feature("i1").set("maxlinit", 100);
    model.sol("sol4").feature("t1").feature("i1").set("nlinnormuse", "on");
    model.sol("sol4").feature("t1").feature("i1").label("AMG\uff0c\u6d41\u4f53\u6d41\u52a8\u53d8\u91cf (spf2)");
    model.sol("sol4").feature("t1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").set("maxcoarsedof", 80000);
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").set("strconn", 0.02);
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").set("compactaggregation", false);
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").feature("pr").create("sc1", "SCGS");
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("iter", 0);
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("scgsrelax", 0.7);
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsmethod", "lines");
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("relax", 0.5);
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgssolv", "stored");
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").feature("po").create("sc1", "SCGS");
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1").set("iter", 1);
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1").set("scgsrelax", 0.7);
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsmethod", "lines");
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1").set("relax", 0.5);
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgssolv", "stored");
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol4").feature("t1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol4").feature("t1").create("i2", "Iterative");
    model.sol("sol4").feature("t1").feature("i2").set("linsolver", "gmres");
    model.sol("sol4").feature("t1").feature("i2").set("prefuntype", "left");
    model.sol("sol4").feature("t1").feature("i2").set("itrestart", 50);
    model.sol("sol4").feature("t1").feature("i2").set("rhob", 20);
    model.sol("sol4").feature("t1").feature("i2").set("maxlinit", 200);
    model.sol("sol4").feature("t1").feature("i2").set("nlinnormuse", "on");
    model.sol("sol4").feature("t1").feature("i2").label("AMG\uff0c\u6e4d\u6d41\u53d8\u91cf (spf2)");
    model.sol("sol4").feature("t1").feature("i2").create("mg1", "Multigrid");
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").set("prefun", "saamg");
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").set("mgcycle", "v");
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").set("strconn", 0.01);
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").set("nullspace", "constant");
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").set("usesmooth", false);

    return model;
  }

  public static Model run3(Model model) {
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").set("saamgcompwise", true);
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").set("loweramg", true);
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").set("compactaggregation", false);
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("iter", 0);
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("linerelax", 0.7);
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linemethod", "uncoupled");
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("seconditer", 1);
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("relax", 0.5);
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1").set("iter", 1);
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1").set("linerelax", 0.7);
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1")
         .set("linemethod", "uncoupled");
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1").set("seconditer", 1);
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1").set("relax", 0.5);
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol4").feature("t1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol4").feature("t1").feature().remove("fcDef");
    model.sol("sol4").attach("std3");

    model.batch().create("p1", "Parametric");
    model.batch("p1").study("std3");
    model.batch("p1").create("so1", "Solutionseq");
    model.batch("p1").feature("so1").set("seq", "sol4");
    model.batch("p1").feature("so1").set("store", "on");
    model.batch("p1").feature("so1").set("clear", "on");
    model.batch("p1").feature("so1").set("psol", "none");
    model.batch("p1").set("pname", new String[]{"alpha0"});
    model.batch("p1").set("plistarr", new String[]{"0,2,4,6"});
    model.batch("p1").set("sweeptype", "sparse");
    model.batch("p1").set("probesel", "all");
    model.batch("p1").set("probes", new String[]{});
    model.batch("p1").set("plot", "off");
    model.batch("p1").set("err", "on");
    model.batch("p1").attach("std3");
    model.batch("p1").set("control", "param");

    model.sol("sol4").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol4").feature("t1").set("maxstepbdf", 0.0025);
    model.sol("sol4").feature("t1").set("minorder", 2);
    model.sol("sol4").feature("t1").set("rescaleafterinitbw", true);
    model.sol().create("sol6");
    model.sol("sol6").study("std3");
    model.sol("sol6").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol6");
    model.batch("p1").run("compute");

    model.result().dataset("dset6").set("geom", "geom1");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u901f\u5ea6 (spf2)");
    model.result("pg5").set("data", "dset6");
    model.result("pg5").setIndex("looplevel", 46, 0);
    model.result("pg5").setIndex("looplevel", 4, 1);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("data", "dset6");
    model.result("pg5").setIndex("looplevel", 46, 0);
    model.result("pg5").setIndex("looplevel", 4, 1);
    model.result("pg5").set("defaultPlotID", "ResultDefaults_SinglePhaseFlow/icom1/pdef1/pcond1/pg1");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u8868\u9762");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("expr", "spf2.U");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u538b\u529b (spf2)");
    model.result("pg6").set("data", "dset6");
    model.result("pg6").setIndex("looplevel", 46, 0);
    model.result("pg6").setIndex("looplevel", 4, 1);
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("data", "dset6");
    model.result("pg6").setIndex("looplevel", 46, 0);
    model.result("pg6").setIndex("looplevel", 4, 1);
    model.result("pg6").set("defaultPlotID", "ResultDefaults_SinglePhaseFlow/icom1/pdef1/pcond1/pg2");
    model.result("pg6").feature().create("con1", "Contour");
    model.result("pg6").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg6").feature("con1").set("showsolutionparams", "on");
    model.result("pg6").feature("con1").set("expr", "p2");
    model.result("pg6").feature("con1").set("number", 40);
    model.result("pg6").feature("con1").set("levelrounding", false);
    model.result("pg6").feature("con1").set("smooth", "internal");
    model.result("pg6").feature("con1").set("showsolutionparams", "on");
    model.result("pg6").feature("con1").set("data", "parent");
    model.result().dataset().create("edg2", "Edge2D");
    model.result().dataset("edg2").label("\u5916\u58c1 1");
    model.result().dataset("edg2").set("data", "dset6");
    model.result().dataset("edg2").selection().geom("geom1", 1);
    model.result().dataset("edg2").selection().set(13, 14);
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u58c1\u5206\u8fa8\u7387 (spf2)");
    model.result("pg7").set("data", "dset6");
    model.result("pg7").setIndex("looplevel", 46, 0);
    model.result("pg7").setIndex("looplevel", 4, 1);
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").set("data", "dset6");
    model.result("pg7").setIndex("looplevel", 46, 0);
    model.result("pg7").setIndex("looplevel", 4, 1);
    model.result("pg7").set("defaultPlotID", "ResultDefaults_SinglePhaseFlow/icom1/pdef1/pcond1/pcond2/pcond4/pg2");
    model.result("pg7").feature().create("line1", "Line");
    model.result("pg7").feature("line1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg7").feature("line1").set("showsolutionparams", "on");
    model.result("pg7").feature("line1").set("expr", "spf2.Delta_wPlus");
    model.result("pg7").feature("line1").set("linetype", "tube");
    model.result("pg7").feature("line1").set("smooth", "internal");
    model.result("pg7").feature("line1").set("showsolutionparams", "on");
    model.result("pg7").feature("line1").set("data", "parent");
    model.result("pg7").feature("line1").feature().create("hght1", "Height");
    model.result("pg7").feature("line1").feature("hght1").label("\u9ad8\u5ea6\u8868\u8fbe\u5f0f");
    model.result("pg7").feature("line1").feature("hght1").set("heightdata", "expr");
    model.result("pg7").feature("line1").feature("hght1").set("expr", "spf2.WRHeightExpr");
    model.result("pg5").run();

    model.component("comp1").view("view1").axis().set("xmin", -0.4);
    model.component("comp1").view("view1").axis().set("xmax", 1.4);
    model.component("comp1").view("view1").axis().set("ymin", -0.4);
    model.component("comp1").view("view1").axis().set("ymax", 0.4);
    model.component("comp1").view("view1").set("locked", true);

    model.result().dataset().create("tavg1", "TimeAverage");
    model.result().dataset("tavg1").set("data", "edg2");
    model.result().dataset("tavg1").setIndex("looplevelinput", "first", 1);
    model.result().dataset("tavg1").setIndex("looplevelinput", "manual", 0);
    model.result().dataset("tavg1")
         .setIndex("looplevel", new int[]{6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46}, 0);
    model.result().dataset().create("tavg2", "TimeAverage");
    model.result().dataset("tavg2").set("data", "edg2");
    model.result().dataset("tavg2").setIndex("looplevelinput", "manual", 1);
    model.result().dataset("tavg2").setIndex("looplevel", new int[]{2}, 1);
    model.result().dataset("tavg2").setIndex("looplevelinput", "manual", 0);
    model.result().dataset("tavg2")
         .setIndex("looplevel", new int[]{6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46}, 0);
    model.result().dataset().create("tavg3", "TimeAverage");
    model.result().dataset("tavg3").set("data", "edg2");
    model.result().dataset("tavg3").setIndex("looplevelinput", "manual", 1);
    model.result().dataset("tavg3").setIndex("looplevel", new int[]{3}, 1);
    model.result().dataset("tavg3").setIndex("looplevelinput", "manual", 0);
    model.result().dataset("tavg3")
         .setIndex("looplevel", new int[]{6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46}, 0);
    model.result().dataset().create("tavg4", "TimeAverage");
    model.result().dataset("tavg4").set("data", "edg2");
    model.result().dataset("tavg4").setIndex("looplevelinput", "manual", 1);
    model.result().dataset("tavg4").setIndex("looplevel", new int[]{4}, 1);
    model.result().dataset("tavg4").setIndex("looplevelinput", "manual", 0);
    model.result().dataset("tavg4")
         .setIndex("looplevel", new int[]{6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46}, 0);
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").setIndex("looplevelinput", "first", 0);
    model.result().numerical("gev1").setIndex("expr", "C_lift", 0);
    model.result().numerical().duplicate("gev2", "gev1");
    model.result().numerical("gev2").setIndex("looplevelinput", "manual", 0);
    model.result().numerical("gev2").setIndex("looplevel", new int[]{2}, 0);
    model.result().numerical().duplicate("gev3", "gev2");
    model.result().numerical("gev3").setIndex("looplevel", new int[]{3}, 0);
    model.result().numerical().duplicate("gev4", "gev3");
    model.result().numerical("gev4").setIndex("looplevel", new int[]{4}, 0);
    model.result().numerical().duplicate("gev5", "gev4");
    model.result().numerical("gev5").set("data", "tavg1");
    model.result().numerical("gev5").setIndex("expr", "C_lift_t", 0);
    model.result().numerical().duplicate("gev6", "gev5");
    model.result().numerical("gev6").set("data", "tavg2");
    model.result().numerical().duplicate("gev7", "gev6");
    model.result().numerical("gev7").set("data", "tavg3");
    model.result().numerical().duplicate("gev8", "gev7");
    model.result().numerical("gev8").set("data", "tavg4");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").importData("eppler_airfoil_transition_A0a_0_res.txt");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").importData("eppler_airfoil_transition_A0a_2_res.txt");
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").importData("eppler_airfoil_transition_A0a_4_res.txt");
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").importData("eppler_airfoil_transition_A0a_6_res.txt");
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").run();
    model.result("pg4").feature("line1").set("radiusexpr", "0.01");
    model.result("pg4").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg4").feature("line1").set("tuberadiusscale", 1);
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").set("edges", false);
    model.result("pg5").run();
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("line1").set("radiusexpr", "0.01");
    model.result("pg7").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg7").feature("line1").set("tuberadiusscale", 1);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr1").set("linewidth", "preference");
    model.result("pg8").feature("lngr1").set("data", "tavg1");
    model.result("pg8").feature("lngr1").set("expr", "-p2/(rho0*U0^2/2)");
    model.result("pg8").feature("lngr1").set("xdata", "expr");
    model.result("pg8").feature("lngr1").set("xdataexpr", "x");
    model.result("pg8").feature("lngr1").set("legend", true);
    model.result("pg8").feature("lngr1").set("legendmethod", "manual");
    model.result("pg8").feature("lngr1").setIndex("legends", "SST+Transition", 0);
    model.result("pg8").run();
    model.result("pg8").create("lngr2", "LineGraph");
    model.result("pg8").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr2").set("linewidth", "preference");
    model.result("pg8").feature("lngr2").set("data", "edg1");
    model.result("pg8").feature("lngr2").setIndex("looplevelinput", "first", 0);
    model.result("pg8").feature("lngr2").set("expr", "-p/(rho0*U0^2/2)");
    model.result("pg8").feature("lngr2").set("xdata", "expr");
    model.result("pg8").feature("lngr2").set("xdataexpr", "x");
    model.result("pg8").feature("lngr2").set("legend", true);
    model.result("pg8").feature("lngr2").set("legendmethod", "manual");
    model.result("pg8").feature("lngr2").setIndex("legends", "SST", 0);
    model.result("pg8").run();
    model.result("pg8").create("tblp1", "Table");
    model.result("pg8").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg8").feature("tblp1").set("linewidth", "preference");
    model.result("pg8").feature("tblp1").set("linestyle", "none");
    model.result("pg8").feature("tblp1").set("linecolor", "black");
    model.result("pg8").feature("tblp1").set("linemarker", "point");
    model.result("pg8").feature("tblp1").set("legend", true);
    model.result("pg8").feature("tblp1").set("legendmethod", "manual");
    model.result("pg8").feature("tblp1").setIndex("legends", "Experiment", 0);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature("lngr1").set("data", "tavg2");
    model.result("pg9").run();
    model.result("pg9").feature("lngr2").setIndex("looplevelinput", "manual", 0);
    model.result("pg9").feature("lngr2").setIndex("looplevel", new int[]{2}, 0);
    model.result("pg9").run();
    model.result("pg9").feature("tblp1").set("table", "tbl2");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").feature("lngr1").set("data", "tavg3");
    model.result("pg10").run();
    model.result("pg10").feature("lngr2").setIndex("looplevel", new int[]{3}, 0);
    model.result("pg10").run();
    model.result("pg10").feature("tblp1").set("table", "tbl3");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result().duplicate("pg11", "pg10");
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").feature("lngr1").set("data", "tavg4");
    model.result("pg11").run();
    model.result("pg11").feature("lngr2").setIndex("looplevel", new int[]{4}, 0);
    model.result("pg11").run();
    model.result("pg11").feature("tblp1").set("table", "tbl4");
    model.result("pg11").run();

    model.title("Eppler \u7ffc\u578b\u8f6c\u6369");

    model
         .description("\u672c\u4f8b\u4f7f\u7528 SST \u6e4d\u6d41\u6a21\u578b\uff08\u5e26\u6216\u4e0d\u5e26\u8f6c\u6369\u6a21\u578b\uff09\u8ba1\u7b97 Eppler 387 \u7ffc\u578b\u5468\u56f4\u7684\u6d41\u52a8\uff0c\u5e76\u5c06\u7ed3\u679c\u4e0e\u5b9e\u9a8c\u503c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();

    model.label("eppler_airfoil_transition.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
