/*
 * liquid_liquid_extraction.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:20 by COMSOL 6.3.0.290. */
public class liquid_liquid_extraction {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Mixing_and_Separation");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mm", "VolumeAveragedMixtureModelkomega", "geom1");
    model.component("comp1").physics().create("tds", "DilutedSpeciesInContinuousPhase", "geom1");
    model.component("comp1").physics("tds").field("concentration").field("cc");
    model.component("comp1").physics("tds").field("concentration").component(new String[]{"cc"});
    model.component("comp1").physics().create("tds2", "DilutedSpeciesInDispersedPhase", "geom1");
    model.component("comp1").physics("tds2").field("concentration").field("cd");
    model.component("comp1").physics("tds2").field("concentration").component(new String[]{"cd"});

    model.component("comp1").multiphysics().create("dds1", "DispersedTwoPhaseFlowDS", 2);
    model.component("comp1").multiphysics("dds1").set("Fluid_physics", "mm");
    model.component("comp1").multiphysics("dds1").set("ContinuousPhaseSpecies_physics", "tds");
    model.component("comp1").multiphysics("dds1").set("DispersedPhaseSpecies_physics", "tds2");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/mm", true);
    model.study("std1").feature("time").setSolveFor("/physics/tds", true);
    model.study("std1").feature("time").setSolveFor("/physics/tds2", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/dds1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("km", "1e-9[m^2/s]/100e-6[m]", "\u8d28\u91cf\u4f20\u9012\u7cfb\u6570");
    model.param().set("r_d", "5e-4[m]", "\u6db2\u6ef4\u534a\u5f84");
    model.param().set("K_p", "0.1", "\u5206\u914d\u6761\u4ef6");
    model.param().set("D1", "1e-9[m^2/s]", "\u6269\u6563\u7cfb\u6570");
    model.param().set("cd_0", "10[mol/m^3]", "\u5165\u53e3\u6d53\u5ea6\uff0c\u5206\u6563\u76f8");
    model.param().set("rho_d", "900[kg/m^3]", "\u6db2\u6ef4\u5bc6\u5ea6");
    model.param().set("mu_d", "1e-3[kg/m/s]", "\u6db2\u6ef4\u9ecf\u5ea6");
    model.param().set("H_c", "0.8[m]", "\u67f1\u9ad8");
    model.param().set("R_c", "0.1[m]", "\u67f1\u534a\u5f84");
    model.param().set("t_c", "3[mm]", "\u67f1\u58c1\u539a");
    model.param().set("n_s", "4", "\u7ea7\u6570");
    model.param().set("hsep_s", "0.1[m]", "\u7ea7\u95f4\u9ad8\u5ea6");
    model.param().set("h0_s", "11[cm]", "\u7b2c\u4e00\u7ea7\u9ad8\u5ea6");
    model.param().set("W_sep", "1[cm]", "\u5165\u53e3/\u51fa\u53e3\u5206\u79bb\u5668\u5bbd\u5ea6");
    model.param().set("H_sep", "5[cm]", "\u5165\u53e3/\u51fa\u53e3\u5206\u79bb\u5668\u9ad8\u5ea6");
    model.param().set("vf0", "0.5", "\u5206\u6563\u76f8\u5165\u53e3\u4f53\u79ef\u5206\u6570");

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", 0.64);
    model.component("comp1").func("step1").set("from", 1);
    model.component("comp1").func("step1").set("to", 0);
    model.component("comp1").func("step1").set("smooth", 0.15);
    model.component("comp1").func().create("step2", "Step");
    model.component("comp1").func("step2").set("location", 0.5);
    model.component("comp1").func("step2").set("smooth", "1[s]");
    model.component("comp1").func().create("step3", "Step");
    model.component("comp1").func("step3").set("location", 2.25);
    model.component("comp1").func("step3").set("smooth", "0.5[s]");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"R_c", "H_c"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "t_c", 0);
    model.component("comp1").geom("geom1").feature("r1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"W_sep", "H_sep"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"R_c*0.4", "0"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("r2");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("pos", new String[]{"0", "H_c/2"});
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "h0_s", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "R_c/2", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "h0_s", 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "R_c/2", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "h0_s-0.5[cm]", 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "h0_s-1[cm]", 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 4, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "h0_s", 4, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("pol1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new String[]{"1", "n_s"});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"0", "hsep_s*2"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("mir2", "Mirror");
    model.component("comp1").geom("geom1").feature("mir2").selection("input")
         .set("arr1(1,1)", "arr1(1,2)", "arr1(1,3)");
    model.component("comp1").geom("geom1").feature("mir2").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir2").set("pos", new String[]{"(R_c-t_c)/2", "0"});
    model.component("comp1").geom("geom1").run("mir2");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("mir2");
    model.component("comp1").geom("geom1").feature("mov1").set("disply", "hsep_s");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"R_c*0.4*0.9", "1"});
    model.component("comp1").geom("geom1").feature("r3").setIndex("size", "h0_s*0.85", 1);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mcd1", "MeshControlDomains");
    model.component("comp1").geom("geom1").feature("mcd1").selection("input").set("fin", 1);
    model.component("comp1").geom("geom1").run("mcd1");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").set(1);

    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").selection().set(1);
    model.component("comp1").cpl().create("minop1", "Minimum");
    model.component("comp1").cpl("minop1").selection().all();

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

    model.component("comp1").physics("mm").selection().named("sel1");
    model.component("comp1").physics("mm").prop("PhysicalModelProperty")
         .set("DispersedPhase", "LiquidDropletsBubbles");
    model.component("comp1").physics("mm").prop("PhysicalModelProperty").set("SlipModel", "SchillerNaumann");
    model.component("comp1").physics("mm").feature("mp1").set("DispersedPhase", "dommat");
    model.component("comp1").physics("mm").feature("mp1").set("rhod_mat", "userdef");
    model.component("comp1").physics("mm").feature("mp1").set("rhod", "rho_d");
    model.component("comp1").physics("mm").feature("mp1").set("mud_mat", "userdef");
    model.component("comp1").physics("mm").feature("mp1").set("mud", "mu_d");
    model.component("comp1").physics("mm").feature("mp1").set("diam", "2*r_d");
    model.component("comp1").physics("mm").feature("mp1").set("MixtureViscosityModel", "VolumeAveraged");
    model.component("comp1").physics("mm").create("gr1", "Gravity", 2);
    model.component("comp1").physics("mm").feature("gr1").selection().set(1);
    model.component("comp1").physics("mm").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("mm").feature("out1").selection().set(36);
    model.component("comp1").physics("mm").feature("out1").set("SuppressBackflow", false);
    model.component("comp1").physics("mm").feature("out1").set("ExteriorDispOption", true);
    model.component("comp1").physics("mm").feature("out1").set("phid0", "1e-6");
    model.component("comp1").physics("mm").create("out2", "OutletBoundary", 1);
    model.component("comp1").physics("mm").feature("out2").selection().set(42);
    model.component("comp1").physics("mm").feature("out2").set("p0", "-g_const*mm.rho*z");
    model.component("comp1").physics("mm").feature("out2").set("ExteriorDispOption", true);
    model.component("comp1").physics("mm").feature("out2").set("phid0", "1e-6");
    model.component("comp1").physics("mm").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("mm").feature("inl1").selection().set(2);
    model.component("comp1").physics("mm").feature("inl1").set("U0in", "nojac(mm.jslipz)");
    model.component("comp1").physics("mm").feature("inl1").set("phid0", "vf0*step2(t)");
    model.component("comp1").physics("mm").create("inl2", "InletBoundary", 1);
    model.component("comp1").physics("mm").feature("inl2").selection().set(19);
    model.component("comp1").physics("mm").feature("inl2").set("U0in", "0.01[m/s]*step2(t)");
    model.component("comp1").physics("tds").selection().named("sel1");
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_cc", new String[]{"D1", "0", "0", "0", "D1", "0", "0", "0", "D1"});
    model.component("comp1").physics("tds").create("in1", "Inflow", 1);
    model.component("comp1").physics("tds").feature("in1").selection().set(19);
    model.component("comp1").physics("tds").create("open1", "OpenBoundary", 1);
    model.component("comp1").physics("tds").feature("open1").selection().set(2, 36, 42);
    model.component("comp1").physics("tds").feature("open1").setIndex("c0", "1e-6", 0);
    model.component("comp1").physics("tds2").selection().named("sel1");
    model.component("comp1").physics("tds2").feature("cdm1")
         .set("D_cd", new String[]{"D1", "0", "0", "0", "D1", "0", "0", "0", "D1"});
    model.component("comp1").physics("tds2").create("in1", "Inflow", 1);
    model.component("comp1").physics("tds2").feature("in1").selection().set(2);
    model.component("comp1").physics("tds2").feature("in1").setIndex("c0", "cd_0*vf0*step3(t)", 0);
    model.component("comp1").physics("tds2").create("open1", "OpenBoundary", 1);
    model.component("comp1").physics("tds2").feature("open1").selection().set(19, 36, 42);
    model.component("comp1").physics("tds2").feature("open1").setIndex("c0", "1e-6", 0);

    model.component("comp1").multiphysics("dds1").set("phaseTransferredSpecies_cc", true);
    model.component("comp1").multiphysics("dds1").set("km_cc", "km*step1(phid)");
    model.component("comp1").multiphysics("dds1").set("Kp_cc", "K_p");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 6);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.005);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 4.0E-5);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.25);
    model.component("comp1").mesh("mesh1").feature("cr1").set("minangle", 200);
    model.component("comp1").mesh("mesh1").feature("cr1").set("refinement", 0.2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().remaining();
    model.component("comp1").mesh("mesh1").feature("bl1").set("sharpcorners", "split");
    model.component("comp1").mesh("mesh1").feature("bl1").set("splitdivangle", 45);
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothmaxiter", 10);
    model.component("comp1").mesh("mesh1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size2").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("size2").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size2").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature().move("size2", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("time").set("tlist", "range(0,1,210)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_cc").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_cd").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_dds1_cWall_d_cc").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_dds1_cWall_d_cd").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_dds1_cWall_u_cc").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_dds1_cWall_u_cd").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_j").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_j").set("scaleval", 0.1);
    model.sol("sol1").feature("v1").feature("comp1_p").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_p").set("scaleval", "1e4");
    model.sol("sol1").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol1").feature("t1").set("maxstepbdf", "5e-3");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("revangle", 180);
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").selection().geom("geom1", 2);
    model.result().dataset("rev2").selection().geom("geom1", 2);
    model.result().dataset("rev2").selection().set(2, 3, 4, 5, 8, 9, 10);
    model.result().dataset().create("rev3", "Revolve2D");
    model.result().dataset("rev3").set("revangle", 225);
    model.result().dataset("rev3").selection().geom("geom1", 2);
    model.result().dataset("rev3").selection().geom("geom1", 2);
    model.result().dataset("rev3").selection().set(6, 7, 11);
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("quickplane", "xz");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("plotarrayenable", true);
    model.result("pg1").set("relpadding", 0.5);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("arraydim", "1");
    model.result("pg1").feature("surf1").set("data", "dset1");
    model.result("pg1").feature("surf1").setIndex("looplevel", 11, 0);
    model.result("pg1").feature("surf1").set("expr", "mm.J");
    model.result("pg1").feature("surf1").set("descr", "\u901f\u5ea6\u573a\uff0c\u6df7\u5408\u7269");
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("arraydim", "1");
    model.result("pg1").feature("str1").set("data", "dset1");
    model.result("pg1").feature("str1").setIndex("looplevel", 11, 0);
    model.result("pg1").feature("str1").set("expr", new String[]{"mm.jdr", "mm.jdz"});
    model.result("pg1").feature("str1").set("descr", "\u5206\u6563\u76f8\u901a\u91cf");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.02);
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature().duplicate("str2", "str1");
    model.result("pg1").feature("str2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("str2").set("expr", new String[]{"mm.jcr", "mm.jcz"});
    model.result("pg1").feature("str2").set("descr", "\u8fde\u7eed\u76f8\u901a\u91cf");
    model.result("pg1").create("ann1", "Annotation");
    model.result("pg1").feature("ann1").set("arraydim", "1");
    model.result("pg1").feature("ann1").set("posxexpr", -0.035);
    model.result("pg1").feature("ann1").set("showpoint", false);
    model.result("pg1").feature("ann1").set("orientation", "vertical");
    model.result("pg1").feature("ann1").set("manualindexing", true);
    model.result("pg1").feature().duplicate("ann2", "ann1");
    model.result("pg1").feature("ann2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("ann2").set("arrayindex", 1);
    model.result("pg1").feature().duplicate("ann3", "ann2");
    model.result("pg1").feature("ann3").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("ann3").set("arrayindex", 2);
    model.result("pg1").feature("surf1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").feature().duplicate("str3", "str1");
    model.result("pg1").feature().duplicate("str4", "str2");
    model.result("pg1").feature().duplicate("ann4", "ann1");
    model.result("pg1").feature().duplicate("ann5", "ann2");
    model.result("pg1").feature().duplicate("ann6", "ann3");
    model.result("pg1").feature("surf2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").setIndex("looplevel", 211, 0);
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");
    model.result("pg1").feature("str3").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("str3").setIndex("looplevel", 211, 0);
    model.result("pg1").feature("str4").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("str4").setIndex("looplevel", 211, 0);
    model.result("pg1").feature("ann4").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("ann4").set("arrayindex", 3);
    model.result("pg1").feature("ann5").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("ann5").set("arrayindex", 4);
    model.result("pg1").feature("ann6").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("ann6").set("arrayindex", 5);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("edges", false);
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").set("plotarrayenable", true);
    model.result("pg2").set("paddinglinear", "absolute");
    model.result("pg2").set("padding", 0.2);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("arraydim", "1");
    model.result("pg2").feature("surf1").set("data", "rev2");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").feature("surf1").set("manualindexing", true);
    model.result("pg2").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").feature("surf1").feature("mtrl1").set("family", "steelanodized");
    model.result("pg2").feature("surf1").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf2", "surf1");
    model.result("pg2").feature("surf2").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("data", "rev3");
    model.result("pg2").create("surf3", "Surface");
    model.result("pg2").feature("surf3").set("arraydim", "1");
    model.result("pg2").feature("surf3").set("expr", "mm.Ud");
    model.result("pg2").feature("surf3").set("descr", "\u901f\u5ea6\u573a\uff0c\u5206\u6563\u76f8");
    model.result("pg2").feature("surf3").set("manualindexing", true);
    model.result("pg2").create("str1", "StreamlineSurface");
    model.result("pg2").feature("str1").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("str1").set("arraydim", "1");
    model.result("pg2").feature("str1").set("data", "cpl1");
    model.result("pg2").feature("str1").set("expr", new String[]{"mm.udr", "mm.udphi", "mm.udz"});
    model.result("pg2").feature("str1").set("descr", "\u901f\u5ea6\u573a\uff0c\u5206\u6563\u76f8");
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("udist", 0.015);
    model.result("pg2").feature("str1").set("linetype", "tube");
    model.result("pg2").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("str1").set("tuberadiusscale", 0.0015);
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowscaleactive", true);
    model.result("pg2").feature("str1").set("arrowscale", 0.2);
    model.result("pg2").feature("str1").set("color", "custom");
    model.result("pg2").feature("str1").set("customcolor", new double[]{1, 0.6274510025978088, 0.47843137383461});
    model.result("pg2").feature("str1").set("manualindexing", true);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").create("ann1", "Annotation");
    model.result("pg2").feature("ann1").set("arraydim", "1");
    model.result("pg2").feature("ann1").set("posxexpr", "1.5*R_c");
    model.result("pg2").feature("ann1").set("poszexpr", "H_c/2");
    model.result("pg2").feature("ann1").set("showpoint", false);
    model.result("pg2").feature("ann1").set("anchorpoint", "center");
    model.result("pg2").feature("ann1").set("orientation", "vertical");
    model.result("pg2").feature("surf1").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf4", "surf1");
    model.result("pg2").feature().duplicate("surf5", "surf2");
    model.result("pg2").feature().duplicate("surf6", "surf3");
    model.result("pg2").feature().duplicate("str2", "str1");
    model.result("pg2").feature().duplicate("ann2", "ann1");
    model.result("pg2").feature("surf4").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf4").set("arrayindex", 1);
    model.result("pg2").feature("surf5").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf5").set("arrayindex", 1);
    model.result("pg2").feature("surf6").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf6").set("expr", "mm.Uc");
    model.result("pg2").feature("surf6").set("descr", "\u901f\u5ea6\u573a\uff0c\u8fde\u7eed\u76f8");
    model.result("pg2").feature("surf6").set("inheritplot", "surf3");
    model.result("pg2").feature("surf6").set("arrayindex", 1);
    model.result("pg2").feature("str2").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("str2").set("expr", new String[]{"mm.ucr", "mm.ucphi", "mm.ucz"});
    model.result("pg2").feature("str2").set("descr", "\u901f\u5ea6\u573a\uff0c\u8fde\u7eed\u76f8");
    model.result("pg2").feature("str2").set("arrayindex", 1);
    model.result("pg2").feature("ann2").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf7", "surf1");
    model.result("pg2").feature().duplicate("surf8", "surf2");
    model.result("pg2").feature().duplicate("surf9", "surf3");
    model.result("pg2").feature().duplicate("ann3", "ann1");
    model.result("pg2").feature("surf7").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf7").set("arrayindex", 2);
    model.result("pg2").feature("surf8").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf8").set("arrayindex", 2);
    model.result("pg2").feature("surf9").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf9").set("expr", "mm.phidReg");
    model.result("pg2").feature("surf9").set("descr", "\u4f53\u79ef\u5206\u6570\uff0c\u5206\u6563\u76f8");
    model.result("pg2").feature("surf9").set("colortable", "AuroraBorealis");
    model.result("pg2").feature("surf9").set("arrayindex", 2);
    model.result("pg2").feature("ann3").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").feature("str1").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature().remove("str1");
    model.result("pg3").feature().remove("str2");
    model.result("pg3").feature("ann1").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf3").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf3").set("expr", "tds2.phs_cd");
    model.result("pg3").feature("surf3").set("descr", "\u76f8\u6001\u6d53\u5ea6");
    model.result("pg3").feature("surf3").set("colortable", "Prism");
    model.result("pg3").feature("ann1").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf6").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf6").set("expr", "tds.phs_cc");
    model.result("pg3").feature("surf6").set("descr", "\u76f8\u6001\u6d53\u5ea6");
    model.result("pg3").feature("ann2").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf9").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf9").set("expr", "dds1.Re_cc");
    model.result("pg3").feature("surf9").set("colortable", "JupiterAuroraBorealis");
    model.result("pg3").feature("ann3").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("edges", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "1");
    model.result("pg4").feature("surf1").set("data", "rev2");
    model.result("pg4").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf1").feature("mtrl1").set("family", "steelanodized");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf2", "surf1");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").set("data", "rev3");
    model.result("pg4").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").create("int1", "IntSurface");
    model.result().evaluationGroup("eg1").feature("int1").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("int1").selection().named("sel1");
    model.result().evaluationGroup("eg1").feature("int1").setIndex("expr", "cc", 0);
    model.result().evaluationGroup("eg1").feature("int1").setIndex("descr", "Amount, Continuous Phase", 0);
    model.result().evaluationGroup("eg1").feature("int1").setIndex("expr", "cd", 1);
    model.result().evaluationGroup("eg1").feature("int1").setIndex("descr", "Amount, Dispersed Phase", 1);
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "none");
    model.result("pg5").create("tblp1", "Table");
    model.result("pg5").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg5").feature("tblp1").set("evaluationgroup", "eg1");
    model.result("pg5").feature("tblp1").set("linewidth", "preference");
    model.result("pg5").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg5").run();
    model.result("pg5").feature("tblp1").set("legend", true);
    model.result("pg5").run();
    model.result("pg5").set("legendpos", "upperleft");
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").create("int1", "IntLine");
    model.result().evaluationGroup("eg2").feature("int1").set("intsurface", true);
    model.result().evaluationGroup("eg2").feature("int1").selection().set(2);
    model.result().evaluationGroup("eg2").feature("int1").setIndex("expr", "mm.jdz*mm.nz", 0);
    model.result().evaluationGroup("eg2").feature("int1").setIndex("descr", "Bottom inlet", 0);
    model.result().evaluationGroup("eg2").create("int2", "IntLine");
    model.result().evaluationGroup("eg2").feature("int2").set("intsurface", true);
    model.result().evaluationGroup("eg2").feature("int2").selection().set(36);
    model.result().evaluationGroup("eg2").feature("int2").setIndex("expr", "mm.jdz*mm.nz", 0);
    model.result().evaluationGroup("eg2").feature("int2").setIndex("descr", "Bottom outlet", 0);
    model.result().evaluationGroup("eg2").create("int3", "IntLine");
    model.result().evaluationGroup("eg2").feature("int3").set("intsurface", true);
    model.result().evaluationGroup("eg2").feature("int3").selection().set(19);
    model.result().evaluationGroup("eg2").feature("int3").setIndex("expr", "mm.jdz*mm.nz", 0);
    model.result().evaluationGroup("eg2").feature("int3").setIndex("descr", "Top inlet", 0);
    model.result().evaluationGroup("eg2").create("int4", "IntLine");
    model.result().evaluationGroup("eg2").feature("int4").set("intsurface", true);
    model.result().evaluationGroup("eg2").feature("int4").selection().set(42);
    model.result().evaluationGroup("eg2").feature("int4").setIndex("expr", "mm.jdz*mm.nz", 0);
    model.result().evaluationGroup("eg2").feature("int4").setIndex("descr", "Top outlet", 0);
    model.result().evaluationGroup("eg2").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "none");
    model.result("pg6").create("tblp1", "Table");
    model.result("pg6").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg6").feature("tblp1").set("evaluationgroup", "eg2");
    model.result("pg6").feature("tblp1").set("linewidth", "preference");
    model.result("pg6").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg6").run();
    model.result("pg6").feature("tblp1").set("legend", true);
    model.result("pg6").run();
    model.result("pg6").set("legendpos", "upperleft");
    model.result().evaluationGroup().duplicate("eg3", "eg2");
    model.result().evaluationGroup("eg3").feature("int1").setIndex("expr", "mm.jcz*mm.nz", 0);
    model.result().evaluationGroup("eg3").feature("int2").setIndex("expr", "mm.jcz*mm.nz", 0);
    model.result().evaluationGroup("eg3").feature("int3").setIndex("expr", "mm.jcz*mm.nz", 0);
    model.result().evaluationGroup("eg3").feature("int4").setIndex("expr", "mm.jcz*mm.nz", 0);
    model.result().evaluationGroup("eg3").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "none");
    model.result("pg7").create("tblp1", "Table");
    model.result("pg7").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg7").feature("tblp1").set("evaluationgroup", "eg3");
    model.result("pg7").feature("tblp1").set("linewidth", "preference");
    model.result("pg7").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg7").run();
    model.result("pg7").feature("tblp1").set("legend", true);
    model.result("pg7").run();
    model.result("pg7").set("legendpos", "upperleft");
    model.result().evaluationGroup().create("eg4", "EvaluationGroup");
    model.result().evaluationGroup("eg4").create("int1", "IntLine");
    model.result().evaluationGroup("eg4").feature("int1").set("intsurface", true);
    model.result().evaluationGroup("eg4").feature("int1").selection().set(2);
    model.result().evaluationGroup("eg4").feature("int1").setIndex("expr", "-tds2.ntflux_cd", 0);
    model.result().evaluationGroup("eg4").feature("int1").setIndex("descr", "Dispersed phase, species inflow", 0);
    model.result().evaluationGroup("eg4").feature("int1").set("dataseries", "integral");
    model.result().evaluationGroup("eg4").feature("int1").set("dataseriescumulative", true);
    model.result().evaluationGroup("eg4").create("int2", "IntLine");
    model.result().evaluationGroup("eg4").feature("int2").set("intsurface", true);
    model.result().evaluationGroup("eg4").feature("int2").selection().set(2, 36, 42);
    model.result().evaluationGroup("eg4").feature("int2").setIndex("expr", "tds.ntflux_cc", 0);
    model.result().evaluationGroup("eg4").feature("int2").setIndex("descr", "Continuous phase, species outflow", 0);
    model.result().evaluationGroup("eg4").feature("int2").set("dataseries", "integral");
    model.result().evaluationGroup("eg4").feature("int2").set("dataseriescumulative", true);
    model.result().evaluationGroup("eg4").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "none");
    model.result("pg8").create("tblp1", "Table");
    model.result("pg8").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg8").feature("tblp1").set("evaluationgroup", "eg4");
    model.result("pg8").feature("tblp1").set("linewidth", "preference");
    model.result("pg8").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg8").run();
    model.result("pg8").feature("tblp1").set("legend", true);
    model.result("pg8").run();
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("legendpos", "upperleft");
    model.result("pg8").run();
    model.result("pg5").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").add("plotgroup", "pg5");
    model.nodeGroup("grp1").add("evaluationgroup", "eg1");

    model.result("pg6").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").add("evaluationgroup", "eg2");

    model.result("pg7").run();

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").add("plotgroup", "pg7");
    model.nodeGroup("grp3").add("evaluationgroup", "eg3");

    model.result("pg8").run();

    model.nodeGroup().create("grp4", "Results");
    model.nodeGroup("grp4").add("plotgroup", "pg8");
    model.nodeGroup("grp4").add("evaluationgroup", "eg4");

    model.title("\u6db2-\u6db2\u8403\u53d6");

    model
         .description("\u6db2-\u6db2\u8403\u53d6\u662f\u4e00\u79cd\u7528\u4e8e\u5728\u4e24\u79cd\u4e0d\u6df7\u6eb6\u6db2\u4f53\u4e4b\u95f4\u5206\u79bb\u6216\u8f6c\u79fb\u7269\u8d28\u7684\u8fc7\u7a0b\uff0c\u7269\u8d28\u5728\u76f8\u5bf9\u6eb6\u89e3\u5ea6\u7684\u5dee\u5f02\u9a71\u52a8\u4e0b\u4ece\u4e00\u79cd\u76f8\u8f6c\u79fb\u5230\u53e6\u4e00\u79cd\u76f8\u3002\u672c\u6a21\u578b\u7814\u7a76\u4e00\u4e2a\u5145\u6ee1\u6c34\u7684\u8403\u53d6\u67f1\u3002\u542b\u6709\u6eb6\u8d28\u7269\u8d28\u7684\u6cb9\u6ef4\u88ab\u6ce8\u5165\u5230\u67f1\u7684\u5e95\u90e8\uff0c\u5e76\u7531\u4e8e\u6d6e\u529b\u800c\u4e0a\u5347\u3002\u968f\u7740\u6cb9\u6ef4\u4e0a\u5347\uff0c\u6eb6\u8d28\u7269\u8d28\u88ab\u8f6c\u79fb\u5230\u6c34\u76f8\u3002\u6c34\u4ece\u67f1\u7684\u9876\u90e8\u6ce8\u5165\u3002\u4e3a\u589e\u52a0\u6cb9\u6ef4\u7684\u505c\u7559\u65f6\u95f4\uff0c\u8be5\u8403\u53d6\u67f1\u914d\u5907\u6709\u591a\u4e2a\u4ea4\u66ff\u7684\u6c34\u5e73\u5706\u76d8\u3002\n\n\u8be5\u6a21\u578b\u901a\u8fc7\u201c\u542b\u7269\u8d28\u4f20\u9012\u7684\u5206\u6563\u4e24\u76f8\u6d41\u201d\u591a\u7269\u7406\u573a\u63a5\u53e3\u8fdb\u884c\u6784\u5efa\uff0c\u4f7f\u7528\u201c\u6df7\u5408\u7269\u6a21\u578b\u201d\u6765\u8ba1\u7b97\u4e24\u76f8\u6d41\uff0c\u5e76\u91c7\u7528 k-\u03c9 \u6a21\u578b\u6765\u8003\u8651\u6cb9\u6ef4\u4e0a\u5347\u5f15\u8d77\u7684\u6e4d\u6d41\uff1b\u6c42\u89e3\u4e86\u8fde\u7eed\u76f8\uff08\u6c34\uff09\u548c\u5206\u6563\u76f8\uff08\u6cb9\u6ef4\uff09\u4e2d\u7684\u7269\u8d28\u4f20\u9012\uff0c\u5e76\u4f7f\u7528\u201c\u5206\u6563\u4e24\u76f8\u6d41\uff0c\u7a00\u7269\u8d28\u201d\u591a\u7269\u7406\u573a\u7279\u5f81\u5bf9\u6eb6\u8d28\u8403\u53d6\u8fdb\u884c\u5efa\u6a21\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("liquid_liquid_extraction.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
