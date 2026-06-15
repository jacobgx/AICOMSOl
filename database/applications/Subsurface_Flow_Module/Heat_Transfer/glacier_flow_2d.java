/*
 * glacier_flow_2d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:31 by COMSOL 6.3.0.290. */
public class glacier_flow_2d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Subsurface_Flow_Module\\Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("dz", "1[m]");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 2);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.param().set("rho_ice", "910[kg/m^3]");
    model.param().descr("rho_ice", "\u51b0\u7684\u5bc6\u5ea6");
    model.param().set("mu_ice", "5e12[Pa*s]");
    model.param().descr("mu_ice", "\u51b0\u7684\u52a8\u529b\u9ecf\u5ea6");
    model.param().set("LSlip", "50[m]");
    model.param().descr("LSlip", "\u6ed1\u79fb\u957f\u5ea6");
    model.param().set("T_init", "-10[degC]");
    model.param().descr("T_init", "\u521d\u59cb\u6e29\u5ea6");
    model.param().set("betaCC", "9.8e-8[K/Pa]");
    model.param().descr("betaCC", "\u514b\u62c9\u4f2f\u9f99\u5e38\u6570");
    model.param().set("n_ice", "3");
    model.param().descr("n_ice", "\u51b0\u7684\u6d41\u53d8\u6307\u6570");
    model.param().set("T_tp", "0.01[degC]");
    model.param().descr("T_tp", "\u6c34\u7684\u4e09\u76f8\u70b9\u6e29\u5ea6");
    model.param().set("p_tp", "611.657[Pa]");
    model.param().descr("p_tp", "\u6c34\u7684\u4e09\u76f8\u70b9\u538b\u529b");
    model.param().set("q_geo", "120[mW/m^2]");
    model.param().descr("q_geo", "\u5730\u70ed\u901a\u91cf");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "glacier_flow_2d_arolla01.txt");
    model.func("int1").importData();
    model.func("int1").label("\u51b0\u5ddd\u57fa\u5e95");
    model.func("int1").setIndex("fununit", "m", 0);
    model.func("int1").setIndex("argunit", "m", 0);
    model.func().create("int2", "Interpolation");
    model.func("int2").set("source", "file");
    model.func("int2").set("filename", "glacier_flow_2d_arolla02.txt");
    model.func("int2").importData();
    model.func("int2").label("\u51b0\u5ddd\u8868\u9762");
    model.func("int2").setIndex("fununit", "m", 0);
    model.func("int2").setIndex("argunit", "m", 0);
    model.func().create("step1", "Step");
    model.func("step1").label("A0");
    model.func("step1").set("funcname", "A0");
    model.func("step1").set("location", 263.15);
    model.func("step1").set("from", "3.985e-13");
    model.func("step1").set("to", "1.916e3");
    model.func("step1").set("smoothactive", false);
    model.func().duplicate("step2", "step1");
    model.func("step2").label("Q");
    model.func("step2").set("funcname", "Q");
    model.func("step2").set("from", "60e3");
    model.func("step2").set("to", "139e3");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("k_ice", "9.828[W/(m*K)]*exp(-0.0057[1/K]*T)");
    model.component("comp1").variable("var1").descr("k_ice", "\u51b0\u7684\u70ed\u5bfc\u7387");
    model.component("comp1").variable("var1").set("cp_ice", "146.3[J/(kg*K)]+7.253[J/(kg*K^2)]*T");
    model.component("comp1").variable("var1").descr("cp_ice", "\u51b0\u7684\u70ed\u5bb9");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").label("m_ice");
    model.component("comp1").func("an1").set("funcname", "m_ice");
    model.component("comp1").func("an1").set("expr", "abs(A0(T)*exp(-Q(T)/(R_const*T)))^(-1/3)*0.5");
    model.component("comp1").func("an1").set("args", "T");
    model.component("comp1").func("an1").set("fununit", "Pa*s");
    model.component("comp1").func("an1").setIndex("argunit", "K", 0);
    model.component("comp1").func("an1").setIndex("plotargs", 260, 0, 1);
    model.component("comp1").func("an1").setIndex("plotargs", 280, 0, 2);
    model.component("comp1").func().create("an2", "Analytic");
    model.component("comp1").func("an2").label("T_m");
    model.component("comp1").func("an2").set("funcname", "T_m");
    model.component("comp1").func("an2").set("expr", "T_tp-betaCC*(p-p_tp)");
    model.component("comp1").func("an2").set("args", "p");
    model.component("comp1").func("an2").set("fununit", "K");
    model.component("comp1").func("an2").setIndex("argunit", "Pa", 0);
    model.component("comp1").func("an2").setIndex("plotargs", 120000, 0, 2);

    model.component("comp1").common().create("ampr1", "AmbientProperties");
    model.component("comp1").common("ampr1").set("AmbientData", "MeteorologicalData2021");
    model.component("comp1").common("ampr1").set("ashrae2021Station", "067170");
    model.component("comp1").common("ampr1").setIndex("ashrae2021ShortDate", "01", 1);
    model.component("comp1").common("ampr1").setIndex("ashrae2021LocalTime", "00", 0);
    model.component("comp1").common("ampr1").set("ashrae2021Temperature", "Low");

    model.component("comp1").geom("geom1").create("pc1", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("pc1").set("parmax", 5000);
    model.component("comp1").geom("geom1").feature("pc1").set("coord", new String[]{"s", "int1(s)"});
    model.component("comp1").geom("geom1").run("pc1");
    model.component("comp1").geom("geom1").create("pc2", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("pc2").set("parmax", 5000);
    model.component("comp1").geom("geom1").feature("pc2").set("coord", new String[]{"s", "int2(s)"});
    model.component("comp1").geom("geom1").run("pc2");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex1").set("pc2", 1);
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex2").set("pc1", 1);
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").selection("vertex1").set("pc2", 2);
    model.component("comp1").geom("geom1").feature("ls2").selection("vertex2").set("pc1", 2);
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("csol1").selection("input").set("ls1", "ls2", "pc1", "pc2");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").axis().set("viewscaletype", "automatic");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u51b0");

    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("StokesFlowProp", true);
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("pref", "ampr1.p_amb");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("rref", new String[]{"x", "int2(x)", "0"});
    model.component("comp1").physics("spf").feature("fp1").set("Constitutiverelation", "InelasticNonNewtonian");
    model.component("comp1").physics("spf").feature("fp1").set("sr_lowlimit", "1e-15[1/s]");
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 1);
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(3);
    model.component("comp1").physics("spf").feature("wallbc2").set("BoundaryCondition", "SlipVelocity");
    model.component("comp1").physics("spf").feature("wallbc2").set("UseViscousSlip", true);
    model.component("comp1").physics("spf").feature("wallbc2").set("Ls", "LSlip");
    model.component("comp1").physics("spf").create("open1", "OpenBoundary", 1);
    model.component("comp1").physics("spf").feature("open1").selection().set(4);
    model.component("comp1").physics("spf").feature("open1")
         .set("CompensateForHydrostaticPressureApproximation", false);
    model.component("comp1").physics("spf").create("open2", "OpenBoundary", 1);
    model.component("comp1").physics("spf").feature("open2").selection().set(1, 2);
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(2);
    model.component("comp1").physics("spf").feature("prpc1")
         .set("CompensateForHydrostaticPressureApproximation", false);
    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("dz", "500[m]");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_init");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(3);
    model.component("comp1").physics("ht").feature("hf1").set("q0_input", "q_geo");
    model.component("comp1").physics("ht").create("open1", "OpenBoundary", 1);
    model.component("comp1").physics("ht").feature("open1").selection().set(1, 2, 4);
    model.component("comp1").physics("ht").feature("open1").set("Tustr_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf2").selection().set(4);
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("HeatTransferCoefficientType", "ExtForcedConvection");
    model.component("comp1").physics("ht").feature("hf2").set("Lpl", 5000);
    model.component("comp1").physics("ht").feature("hf2").set("U_src", "root.comp1.ampr1.v_amb");
    model.component("comp1").physics("ht").feature("hf2").set("pA_src", "root.comp1.ampr1.p_amb");
    model.component("comp1").physics("ht").feature("hf2").set("Text_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("ht").create("sar1", "SurfaceToAmbientRadiation", 1);
    model.component("comp1").physics("ht").feature("sar1").selection().set(4);
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad_mat", "userdef");
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad", 0.97);
    model.component("comp1").physics("ht").feature("sar1").set("Tamb_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(3);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T_m(p)");

    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"k_ice"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho_ice"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"cp_ice"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("PowerLaw", "PowerLaw", "Non-Newtonian_power_law");
    model.component("comp1").material("mat1").propertyGroup("PowerLaw").set("m_pow", new String[]{"m_ice(T)"});
    model.component("comp1").material("mat1").propertyGroup("PowerLaw").set("n_pow", new String[]{"1/n_ice"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 20);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 100);
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", true);
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"spf/wallbc2"});
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tunit", "a");
    model.study("std1").feature("time").set("tlist", "range(0,0.1,10)");
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"spf/wallbc2", "ht/temp1"});
    model.study("std1").setGenPlots(false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u6e29\u5ea6 (ht)");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "T");
    model.result("pg2").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").label("\u6e29\u5ea6 (ht)");
    model.result("pg2").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("unit", "m/yr");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().numerical().create("av1", "AvLine");
    model.result().numerical("av1").set("intsurface", true);
    model.result().numerical("av1").selection().set(1, 2, 4);
    model.result().numerical("av1").set("expr", new String[]{"spf.open1.massFlowRate"});
    model.result().numerical("av1")
         .set("descr", new String[]{"\u6574\u4e2a\u7279\u5f81\u9009\u62e9\u7684\u5411\u5916\u8d28\u91cf\u6d41\u7387"});
    model.result().numerical("av1").set("unit", new String[]{"kg/s"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u7ebf\u5e73\u5747\u503c 1");
    model.result().numerical("av1").set("table", "tbl1");
    model.result().numerical("av1").setResult();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "none");
    model.result("pg3").create("tblp1", "Table");
    model.result("pg3").feature("tblp1").set("source", "table");
    model.result("pg3").feature("tblp1").set("table", "tbl1");
    model.result("pg3").feature("tblp1").set("linewidth", "preference");
    model.result("pg3").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").label("\u5411\u5916\u8d28\u91cf\u6d41\u7387");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/nitf1", true);
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("stat").set("useadvanceddisable", true);

    model.component("comp1").geom("geom1").run();

    model.study("std2").feature("stat").set("disabledphysics", new String[]{"ht/hf1"});
    model.study("std2").feature("time").set("tunit", "a");
    model.study("std2").feature("time").set("tlist", "range(0,0.1,10)");
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"ht/hf1"});
    model.study("std2").setGenPlots(false);
    model.study("std2").showAutoSequences("all");

    model.sol("sol3").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol3").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol3").feature("t1").set("initialstepbdf", "1[s]");

    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().numerical().duplicate("av2", "av1");
    model.result().numerical("av2").set("data", "dset3");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u7ebf\u5e73\u5747\u503c 2");
    model.result().numerical("av2").set("table", "tbl2");
    model.result().numerical("av2").setResult();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup().duplicate("grp2", "grp1");

    model.result("pg4").run();
    model.result("pg4").set("data", "dset3");
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").set("data", "dset3");
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("tblp1").set("table", "tbl2");
    model.result("pg6").feature("tblp1").label("\u5411\u5916\u8d28\u91cf\u6d41\u7387 1");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").run();
    model.result("pg7").label("\u51b0\u539a\u5ea6");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "abs(int1(x)-int2(x))");
    model.result("pg7").feature("surf1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr1").set("linewidth", "preference");
    model.result("pg8").feature("lngr1").selection().set(4);
    model.result("pg8").feature("lngr1").set("expr", "u*tx+v*ty");
    model.result("pg8").feature("lngr1").set("unit", "m/yr");
    model.result("pg8").feature("lngr1").set("legend", true);
    model.result("pg8").feature("lngr1").set("legendmethod", "manual");
    model.result("pg8").feature("lngr1").setIndex("legends", "\u51b7\u51b0\u5ddd", 0);
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevelinput", "last", 0);
    model.result("pg8").create("lngr2", "LineGraph");
    model.result("pg8").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr2").set("linewidth", "preference");
    model.result("pg8").feature("lngr2").set("data", "dset3");
    model.result("pg8").feature("lngr2").setIndex("looplevelinput", "last", 0);
    model.result("pg8").feature("lngr2").selection().set(4);
    model.result("pg8").feature("lngr2").set("expr", "u*tx+v*ty");
    model.result("pg8").feature("lngr2").set("unit", "m/yr");
    model.result("pg8").feature("lngr2").set("legend", true);
    model.result("pg8").feature("lngr2").set("legendmethod", "manual");
    model.result("pg8").feature("lngr2").setIndex("legends", "\u6e29\u51b0\u5ddd", 0);
    model.result("pg8").run();
    model.result("pg8").label("\u6cbf\u51b0\u5ddd\u8868\u9762\u7684\u5207\u5411\u901f\u5ea6");
    model.result("pg8").run();
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("xlabel", "\u8868\u9762\u957f\u5ea6 (m)");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "\u5207\u5411\u901f\u5ea6 (m/yr)");
    model.result("pg8").run();
    model.result("pg8").feature("lngr2").set("titletype", "none");
    model.result("pg7").run();

    model.title("\u51b0\u5ddd\u6d41\u52a8");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u4e8c\u7ef4\u793a\u4f8b\uff0c\u6f14\u793a\u5982\u4f55\u6a21\u62df\u4e24\u79cd\u7c7b\u578b\u7684\u51b0\u5ddd\uff08\u51b7\u51b0\u5ddd\u548c\u6e29\u5e26\u51b0\u5ddd\uff09\u4e2d\u7684\u6d41\u52a8\u548c\u4f20\u70ed\uff0c\u5176\u4e2d\u5c06\u975e\u725b\u987f\u8815\u52a8\u51b0\u6d41\u4e0e\u4f20\u5bfc\u4f20\u70ed\u548c\u5bf9\u6d41\u4f20\u70ed\u8026\u5408\u5728\u4e00\u8d77\uff0c\u4e5f\u53ef\u4ee5\u5c06\u51b0\u5728\u57fa\u5ca9\u4e0a\u7684\u6ed1\u52a8\u8003\u8651\u5728\u5185\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("glacier_flow_2d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
