/*
 * three_phase_mixer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:16 by COMSOL 6.3.0.290. */
public class three_phase_mixer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Mixer_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "TurbulentFlowkeps", "geom1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics().create("phtr", "PhaseTransportFree", "geom1");

    model.component("comp1").multiphysics().create("mfmm1", "MultiphaseFlowMixtureModel", 3);
    model.component("comp1").multiphysics("mfmm1").set("multiphaseflow_physics", "phtr");
    model.component("comp1").multiphysics("mfmm1").set("fluidflow_physics", "spf");
    model.component("comp1").multiphysics("mfmm1").selection().all();

    model.component("comp1").common().create("rot1", "RotatingDomain");
    model.component("comp1").common("rot1").set("rotationType", "rotationalVelocity");
    model.component("comp1").common("rot1").set("rotationalVelocityExpression", "generalRevolutionsPerTime");
    model.component("comp1").common("rot1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/phtr", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/mfmm1", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "three_phase_mixer.mphbin");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");

    model.component("comp1").func().create("rm1", "Ramp");
    model.component("comp1").func("rm1").set("location", 1.55);
    model.component("comp1").func("rm1").set("cutoffactive", true);
    model.component("comp1").func("rm1").set("smoothzonelocactive", true);

    model.component("comp1").geom("geom1").run();

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

    model.component("comp1").common("rot1").selection().set(2);
    model.component("comp1").common("rot1").set("revolutionsPerTime", "100[rpm]*rm1(t*1[1/s])");

    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 2);
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(13);
    model.component("comp1").physics("spf").feature("wallbc2").set("TranslationalVelocityOption", "ZeroFixedWall");
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(2);
    model.component("comp1").physics("phtr").field("volumefraction").component(new String[]{"s1", "s2", "s3"});
    model.component("comp1").physics("phtr").feature("init1").setIndex("s0", 0.1, 1);
    model.component("comp1").physics("phtr").feature("init1").setIndex("s0", 0.1, 2);

    model.component("comp1").multiphysics("mfmm1").set("MixtureViscosityModel", "userdef");
    model.component("comp1").multiphysics("mfmm1")
         .set("mumixture", "mfmm1.muc*max(1-max(0,min(s2+s3,0.999*0.62))/0.62,eps)^(-2.5*0.62)");
    model.component("comp1").multiphysics("mfmm1").set("SlipModel", "HadamardRybczynski");
    model.component("comp1").multiphysics("mfmm1").set("rho_s2_mat", "userdef");
    model.component("comp1").multiphysics("mfmm1").set("rho_s2", "1100[kg/m^3]");
    model.component("comp1").multiphysics("mfmm1").set("rho_s3_mat", "userdef");
    model.component("comp1").multiphysics("mfmm1").set("rho_s3", "850[kg/m^3]");

    model.component("comp1").mesh("mesh1").autoMeshSize(7);
    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blnlayers", 6);

    model.study("std1").feature("time").set("tlist", "range(0,0.5,10)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("bwinitstepfrac", 1);
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("subjtech", "onevery");
    model.sol("sol1").feature("t1").feature("se1").feature("ss4").set("subjtech", "onevery");
    model.sol("sol1").feature("t1").feature("se1").feature("ss4").set("subiter", 2);
    model.sol("sol1").runAll();

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
    model.result().dataset("surf1").selection()
         .set(1, 2, 3, 4, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").setIndex("looplevel", 21, 0);
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
    model.result("pg3").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg3").set("data", "surf1");
    model.result("pg3").setIndex("looplevel", 21, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "spf.Delta_wPlus");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u4f53\u79ef\u5206\u6570 (phtr)");
    model.result("pg4").feature().create("slc1", "Slice");
    model.result("pg4").feature("slc1").label("\u5207\u9762");
    model.result("pg4").feature("slc1").set("showsolutionparams", "on");
    model.result("pg4").feature("slc1").set("expr", "s1");
    model.result("pg4").feature("slc1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg4").feature("slc1").set("colortabletrans", "reverse");
    model.result("pg4").feature("slc1").set("smooth", "internal");
    model.result("pg4").feature("slc1").set("showsolutionparams", "on");
    model.result("pg4").feature("slc1").set("data", "parent");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u4f53\u79ef\u5206\u6570 (phtr) 1");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u8868\u9762");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("expr", "s1");
    model.result("pg5").feature("surf1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg5").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg1").run();

    model.component("comp1").view("view1").set("transparency", true);
    model.component("comp1").view("view1").set("uniformblending", true);
    model.component("comp1").view("view1").set("transparency", false);

    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("quickplane", "xz");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").set("data", "cpl1");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "s2");
    model.result("pg6").feature("surf1").set("rangecoloractive", true);
    model.result("pg6").feature("surf1").set("rangecolormax", 0.62);
    model.result("pg6").feature("surf1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg6").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg6").run();
    model.result("pg6").set("plotarrayenable", true);
    model.result("pg6").set("arrayshape", "square");
    model.result("pg6").set("order", "columnmajor");
    model.result("pg6").setIndex("looplevel", 4, 0);
    model.result("pg6").feature("surf1").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("surf2", "surf1");
    model.result("pg6").feature("surf2").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature("surf2").set("data", "cpl1");
    model.result("pg6").feature("surf2").setIndex("looplevel", 3, 0);
    model.result("pg6").feature("surf2").set("inheritplot", "surf1");
    model.result("pg6").feature().duplicate("surf3", "surf2");
    model.result("pg6").feature("surf3").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature("surf3").setIndex("looplevel", 2, 0);
    model.result("pg6").feature("surf1").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("surf4", "surf1");
    model.result("pg6").feature("surf4").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature("surf4").set("expr", "s3");
    model.result("pg6").feature("surf4").set("inheritplot", "surf1");
    model.result("pg6").feature().duplicate("surf5", "surf4");
    model.result("pg6").feature("surf5").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature("surf5").set("data", "cpl1");
    model.result("pg6").feature("surf5").setIndex("looplevel", 3, 0);
    model.result("pg6").feature().duplicate("surf6", "surf5");
    model.result("pg6").feature("surf6").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature("surf6").setIndex("looplevel", 2, 0);
    model.result("pg6").run();

    model.view("view3").set("showgrid", false);

    model.result("pg6").set("titletype", "none");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("slc1").set("quickplane", "xy");
    model.result("pg1").feature("slc1").set("quickznumber", 3);
    model.result("pg1").feature("slc1").set("interactive", true);
    model.result("pg1").feature("slc1").set("shift", 0.03);
    model.result("pg1").run();
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("xnumber", 10);
    model.result("pg1").feature("arwv1").set("ynumber", 10);
    model.result("pg1").feature("arwv1").set("arrowzmethod", "coord");
    model.result("pg1").feature("arwv1").set("zcoord", "0.08 0.18 0.28");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection()
         .set(2, 3, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg1").feature("surf1").feature("mtrl1").set("family", "steel");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 9, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 21, 0);
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").run();
    model.result("pg4").feature("slc1").set("expr", "s2");
    model.result("pg4").feature("slc1").set("quickxnumber", 1);
    model.result("pg4").feature("slc1").set("coloring", "gradient");
    model.result("pg4").feature("slc1").set("bottomcolor", "blue");
    model.result("pg4").feature("slc1").set("rangecoloractive", true);
    model.result("pg4").feature("slc1").set("rangecolormin", 0);
    model.result("pg4").feature("slc1").set("rangecolormax", 0.62);
    model.result("pg4").feature().duplicate("slc2", "slc1");
    model.result("pg4").run();
    model.result("pg4").feature("slc2").set("expr", "s3");
    model.result("pg4").feature("slc2").set("quickplane", "zx");
    model.result("pg4").feature("slc2").set("quickynumber", 1);
    model.result("pg4").feature("slc2").set("bottomcolor", "red");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").run();
    model.result("pg4").feature().copy("surf2", "pg1/surf2");
    model.result("pg1").feature().remove("surf2");
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 7, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 11, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 15, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 19, 0);
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").set("frametype", "spatial");
    model.result("pg1").run();
    model.result("pg1").run();

    model.title("\u4e09\u76f8\u6df7\u5408\u5668");

    model
         .description("\u672c\u6a21\u578b\u6a21\u62df\u5177\u6709\u8f7b\u9897\u7c92\u548c\u91cd\u9897\u7c92\u7684\u60ac\u6d6e\u6db2\u7684\u5206\u79bb\u548c\u6df7\u5408\u8fc7\u7a0b\u3002\u6700\u521d\uff0c\u8fd9\u4e24\u79cd\u9897\u7c92\u7fa4\u5728\u6574\u4e2a\u6d41\u4f53\u4e2d\u5747\u5300\u5206\u5e03\u3002\u5728\u53f6\u8f6e\u5f00\u59cb\u65cb\u8f6c\u4e4b\u524d\uff0c\u6d41\u4f53\u548c\u4e24\u79cd\u9897\u7c92\u7fa4\u8d8b\u4e8e\u5206\u79bb\uff0c\u56e0\u4e3a\u8f7b\u9897\u7c92\u4f1a\u4e0a\u5347\u5230\u91dc\u7684\u9876\u90e8\uff0c\u800c\u91cd\u9897\u7c92\u4f1a\u6c89\u79ef\u5728\u5e95\u90e8\u3002\u5f53\u60ac\u6d6e\u6db2\u88ab\u6405\u62cc\u65f6\uff0c\u4e09\u76f8\u518d\u6b21\u6df7\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("three_phase_mixer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
