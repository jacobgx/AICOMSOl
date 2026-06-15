/*
 * znbr_flow_battery.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:19 by COMSOL 6.3.0.290. */
public class znbr_flow_battery {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Flow_Batteries");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", "geom1");
    model.component("comp1").physics("tcd").prop("SpeciesProperties")
         .set("ChargeTransportModel", "SupportingElectrolyte");
    model.component("comp1").physics("tcd").field("concentration").field("cBr2");
    model.component("comp1").physics("tcd").field("concentration").component(new String[]{"cBr2"});
    model.component("comp1").physics().create("ge", "GlobalEquations", "geom1");
    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");

    model.study().create("std1");
    model.study("std1").create("cdi", "CurrentDistributionInitialization");
    model.study("std1").feature("cdi").set("ftplistmethod", "manual");
    model.study("std1").feature("cdi").set("solnum", "auto");
    model.study("std1").feature("cdi").set("notsolnum", "auto");
    model.study("std1").feature("cdi").set("outputmap", new String[]{});
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").setSolveFor("/physics/tcd", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/ge", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/tcd", true);
    model.study("std1").feature("time").setSolveFor("/physics/ge", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("A_cell", "3.2*3.2[cm^2]", "\u7535\u6c60\u9762\u79ef");
    model.param().set("H_cell", "A_cell/D_cell", "\u7535\u6c60\u9ad8\u5ea6");
    model.param().set("D_cell", "3.2[cm]", "\u7535\u6c60\u6df1\u5ea6\uff08\u9762\u5916\uff09");
    model.param().set("W_cf", "3[mm]", "\u78b3\u6be1\u5bbd\u5ea6");
    model.param().set("W_sep", "1[mm]", "\u9694\u819c\u5bbd\u5ea6");
    model.param().set("W_cell", "W_cf*2+W_sep", "\u7535\u6c60\u5bbd\u5ea6");
    model.param().set("U", "20[ml/min]/(D_cell*W_cf)", "\u6d41\u901f");
    model.param().set("epsl_cf", "0.5", "\u78b3\u6be1\u5b54\u9699\u7387");
    model.param().set("epsl_sep", "0.5", "\u9694\u819c\u5b54\u9699\u7387");
    model.param().set("sigmas_cf", "100[S/m]", "\u7535\u5bfc\u7387\uff0c\u78b3\u6be1");
    model.param().set("sigmal", "100[S/m]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param().set("T", "293.15[K]", "\u6e29\u5ea6");
    model.param()
         .set("c0_Br2", "1e-6[M]", "\u7528\u4e8e\u521d\u59cb\u7535\u538b\u8ba1\u7b97\u7684 Br2 \u521d\u59cb\u6d53\u5ea6");
    model.param().set("c0_Br_m", "6[M]", "\u6eb4\u79bb\u5b50\u6d53\u5ea6");
    model.param().set("c0_Zn_2p", "4[M]", "\u950c\u79bb\u5b50\u6d53\u5ea6");
    model.param()
         .set("Eeq_ref_Zn", "-0.76[V]+R_const*T/(2*F_const)*log(c0_Zn_2p/1[M])", "\u8d1f\u6781\u53c2\u8003\u5e73\u8861\u7535\u4f4d");
    model.param()
         .set("Eeq_ref_Br", "1.09[V]+R_const*T/(2*F_const)*log((1[M]/c0_Br_m)^2)", "\u6b63\u6781\u53c2\u8003\u5e73\u8861\u7535\u4f4d");
    model.param()
         .set("E_cell_init", "Eeq_ref_Br-Eeq_ref_Zn+R_const*T/(2*F_const)*log(c0_Br2/1[M])", "\u521d\u59cb\u7535\u6c60\u7535\u538b");
    model.param().set("D_Br2", "1.31e-9[m^2/s]", "\u6eb4\u590d\u5408\u7269\u7684\u6269\u6563\u7cfb\u6570");
    model.param()
         .set("i0_ref_Br", "0.1[A/m^2]*(c0_Br_m/1[M])^(2-alpha_a_Br)", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6eb4\u53cd\u5e94");
    model.param()
         .set("i0_ref_Zn", "10[A/m^2]*(c0_Zn_2p/1[M])^((alpha_a_Zn)/2)", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u950c\u53cd\u5e94");
    model.param().set("Av_cf", "1e4[m^2/m^3]", "\u6d3b\u6027\u8868\u9762\u79ef\uff0c\u78b3\u6be1");
    model.param().set("alpha_a_Br", "1", "\u4f20\u9012\u7cfb\u6570\uff0c\u6eb4\u53cd\u5e94");
    model.param().set("alpha_a_Zn", "0.5", "\u4f20\u9012\u7cfb\u6570\uff0c\u950c\u53cd\u5e94");
    model.param().set("V_res", "80[cm^3]", "\u50a8\u5c42\uff08\u7f50\uff09\u4f53\u79ef");
    model.param().set("M_Zn", "65.39[g/mol]", "\u950c\u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("rho_Zn", "7133[kg/m^3]", "\u950c\u7684\u5bc6\u5ea6");
    model.param().set("t_charge", "1800[s]", "\u5145\u7535\u65f6\u95f4");
    model.param().set("i_app", "40[mA/cm^2]", "\u5916\u52a0\u7535\u6d41\u5bc6\u5ea6");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W_cell", "H_cell"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "W_cf", 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "W_sep", 1);
    model.component("comp1").geom("geom1").feature("r1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u9694\u819c");
    model.component("comp1").selection("sel1").set(2);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u8d1f\u6781\u78b3\u6be1");
    model.component("comp1").selection("sel2").set(1);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u6b63\u6781\u78b3\u6be1");
    model.component("comp1").selection("sel3").set(3);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u6b63\u6781\u7535\u89e3\u8d28\u5165\u53e3");
    model.component("comp1").selection("sel4").geom(1);
    model.component("comp1").selection("sel4").set(8);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u6b63\u6781\u7535\u89e3\u8d28\u51fa\u53e3");
    model.component("comp1").selection("sel5").geom(1);
    model.component("comp1").selection("sel5").set(9);

    model.component("comp1").physics("tcd").create("sep1", "Separator", 2);
    model.component("comp1").physics("tcd").feature("sep1").selection().named("sel1");
    model.component("comp1").physics("tcd").feature("sep1")
         .set("D_cBr2", new String[]{"D_Br2", "0", "0", "0", "D_Br2", "0", "0", "0", "D_Br2"});
    model.component("comp1").physics("tcd").feature("sep1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("tcd").feature("sep1")
         .set("sigmal", new String[]{"sigmal", "0", "0", "0", "sigmal", "0", "0", "0", "sigmal"});
    model.component("comp1").physics("tcd").feature("sep1").set("epsl", "epsl_sep");
    model.component("comp1").physics("tcd").create("pce1", "PorousElectrode", 2);
    model.component("comp1").physics("tcd").feature("pce1").label("\u591a\u5b54\u7535\u6781 - \u8d1f\u6781");
    model.component("comp1").physics("tcd").feature("pce1").selection().named("sel2");
    model.component("comp1").physics("tcd").feature("pce1")
         .set("D_cBr2", new String[]{"D_Br2", "0", "0", "0", "D_Br2", "0", "0", "0", "D_Br2"});
    model.component("comp1").physics("tcd").feature("pce1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("tcd").feature("pce1")
         .set("sigmal", new String[]{"sigmal", "0", "0", "0", "sigmal", "0", "0", "0", "sigmal"});
    model.component("comp1").physics("tcd").feature("pce1").set("sigma_mat", "userdef");
    model.component("comp1").physics("tcd").feature("pce1")
         .set("sigma", new String[]{"sigmas_cf", "0", "0", "0", "sigmas_cf", "0", "0", "0", "sigmas_cf"});
    model.component("comp1").physics("tcd").feature("pce1").set("epss", "1-epsl_cf");
    model.component("comp1").physics("tcd").feature("pce1").set("epsl", "epsl_cf");
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").set("nm", 2);
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").set("Eeq_ref", "Eeq_ref_Zn");
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").set("i0_ref", "i0_ref_Zn");
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").set("alphaa", "alpha_a_Zn");
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").set("Av", "Av_cf");
    model.component("comp1").physics("tcd").create("pce2", "PorousElectrode", 2);
    model.component("comp1").physics("tcd").feature("pce2").label("\u591a\u5b54\u7535\u6781 - \u6b63\u6781");
    model.component("comp1").physics("tcd").feature("pce2").selection().named("sel3");
    model.component("comp1").physics("tcd").feature("pce2").set("u", new String[]{"0", "U", "0"});
    model.component("comp1").physics("tcd").feature("pce2")
         .set("D_cBr2", new String[]{"D_Br2", "0", "0", "0", "D_Br2", "0", "0", "0", "D_Br2"});
    model.component("comp1").physics("tcd").feature("pce2").set("sigmal_mat", "userdef");
    model.component("comp1").physics("tcd").feature("pce2")
         .set("sigmal", new String[]{"sigmal", "0", "0", "0", "sigmal", "0", "0", "0", "sigmal"});
    model.component("comp1").physics("tcd").feature("pce2").set("sigma_mat", "userdef");
    model.component("comp1").physics("tcd").feature("pce2")
         .set("sigma", new String[]{"sigmas_cf", "0", "0", "0", "sigmas_cf", "0", "0", "0", "sigmas_cf"});
    model.component("comp1").physics("tcd").feature("pce2").set("epss", "1-epsl_cf");
    model.component("comp1").physics("tcd").feature("pce2").set("epsl", "epsl_cf");
    model.component("comp1").physics("tcd").feature("pce2").feature("per1").set("nm", 2);
    model.component("comp1").physics("tcd").feature("pce2").feature("per1").setIndex("Vi0", -1, 0);
    model.component("comp1").physics("tcd").feature("pce2").feature("per1").set("Eeq_ref", "Eeq_ref_Br");
    model.component("comp1").physics("tcd").feature("pce2").feature("per1").set("i0_ref", "i0_ref_Br");
    model.component("comp1").physics("tcd").feature("pce2").feature("per1").set("alphaa", "alpha_a_Br");
    model.component("comp1").physics("tcd").feature("pce2").feature("per1").set("Av", "Av_cf");
    model.component("comp1").physics("tcd").create("egnd1", "ElectricGround", 1);
    model.component("comp1").physics("tcd").feature("egnd1").selection().set(1);
    model.component("comp1").physics("tcd").create("ec1", "ElectrodeCurrent", 1);
    model.component("comp1").physics("tcd").feature("ec1").selection().set(10);
    model.component("comp1").physics("tcd").feature("ec1").set("ElectronicCurrentType", "AverageCurrentDensity");
    model.component("comp1").physics("tcd").feature("ec1").set("Ias", "i_app");
    model.component("comp1").physics("tcd").feature("ec1").set("phis0init", "E_cell_init");
    model.component("comp1").physics("tcd").create("bei1", "InternalElectrodeSurface", 1);
    model.component("comp1").physics("tcd").feature("bei1").selection().set(4);
    model.component("comp1").physics("tcd").feature("bei1").feature("er1").set("nm", 2);
    model.component("comp1").physics("tcd").feature("bei1").feature("er1").setIndex("Vi0", -1, 0);
    model.component("comp1").physics("tcd").feature("bei1").feature("er1").set("Eeq_ref", "Eeq_ref_Br");
    model.component("comp1").physics("tcd").feature("bei1").feature("er1")
         .set("ElectrodeKinetics", "FastIrreversibleElectrodeReaction");
    model.component("comp1").physics("ge")
         .label("\u5168\u5c40\u5e38\u5fae\u5206\u548c\u5fae\u5206\u4ee3\u6570\u65b9\u7a0b - \u69fd\u6a21\u578b");
    model.component("comp1").physics("ge").feature("ge1").set("name", new String[]{"cBr2_tank"});
    model.component("comp1").physics("ge").feature("ge1")
         .set("equation", new String[]{"cBr2_tankt*V_res+(U*W_cf*cBr2_tank-intop_pos_out(tcd.tflux_cBr2y))*D_cell"});
    model.component("comp1").physics("ge").feature("ge1").set("initialValueU", new int[]{0});
    model.component("comp1").physics("ge").feature("ge1").set("initialValueUt", new int[]{0});
    model.component("comp1").physics("ge").feature("ge1")
         .set("description", new String[]{"Tank and inlet concentration"});

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").label("\u79ef\u5206 - \u6b63\u6781\u7535\u89e3\u8d28\u51fa\u53e3");
    model.component("comp1").cpl("intop1").set("opname", "intop_pos_out");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().named("sel5");

    model.component("comp1").physics("ge").feature("ge1").set("DependentVariableQuantity", "concentration");
    model.component("comp1").physics("ge").feature("ge1").set("CustomSourceTermUnit", "1");
    model.component("comp1").physics("ge").feature("ge1").set("SourceTermQuantity", "none");
    model.component("comp1").physics("ge").feature("ge1").setIndex("CustomSourceTermUnit", "mol/s", 0, 0);
    model.component("comp1").physics("tcd").create("in1", "Inflow", 1);
    model.component("comp1").physics("tcd").feature("in1").selection().named("sel4");
    model.component("comp1").physics("tcd").feature("in1").setIndex("c0", "cBr2_tank", 0);
    model.component("comp1").physics("tcd").feature("in1").set("BoundaryConditionType", "FluxDanckwerts");
    model.component("comp1").physics("tcd").create("out1", "Outflow", 1);
    model.component("comp1").physics("tcd").feature("out1").selection().named("sel5");
    model.component("comp1").physics("tcd").feature("init1").set("initphil", "-Eeq_ref_Zn");
    model.component("comp1").physics("tcd").create("init2", "init", 2);
    model.component("comp1").physics("tcd").feature("init2").selection().named("sel3");
    model.component("comp1").physics("tcd").feature("init2").set("initphil", "-Eeq_ref_Zn");
    model.component("comp1").physics("tcd").feature("init2").set("initphis", "E_cell_init");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(5, 6, 8, 9);
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(1, 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("numelem", 10);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("expr", "tcd.phis0_ec1");
    model.component("comp1").probe("var1").set("descr", "\u8fb9\u754c\u7535\u4f4d");

    model.study("std1").feature("cdi").set("initType", "secondary");
    model.study("std1").feature("cdi").setSolveFor("/physics/ge", false);
    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"tcd.phis0_ec1"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u8fb9\u754c\u7535\u4f4d"});
    model.result("pg2").label("\u5bf9\u5730\u8fb9\u754c\u7535\u6781\u7535\u4f4d (tcd)");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").label("\u7535\u89e3\u8d28\u7535\u4f4d (tcd)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"phil"});
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg3").feature("arws1").set("arrowbase", "center");
    model.result("pg3").feature("arws1").set("color", "gray");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 11, 0);
    model.result("pg4").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (tcd)");
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg4").feature("arws1").set("arrowbase", "center");
    model.result("pg4").feature("arws1").set("color", "gray");
    model.result("pg4").feature("arws1").create("col1", "Color");
    model.result("pg4").feature("arws1").feature("col1").set("expr", "root.comp1.tcd.IlMag");
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", new String[]{"abs(tcd.itot)"});
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("inherittubescale", false);
    model.result("pg4").feature("line1").set("inheritplot", "arws1");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 11, 0);
    model.result("pg5").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (tcd)");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"phis"});
    model.result("pg5").create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").set("expr", new String[]{"tcd.Isx", "tcd.Isy"});
    model.result("pg5").feature("arws1").set("arrowbase", "center");
    model.result("pg5").feature("arws1").set("color", "gray");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 11, 0);
    model.result("pg6").label("\u7535\u6781\u7535\u6d41\u5bc6\u5ea6 (tcd)");
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("expr", new String[]{"tcd.Isx", "tcd.Isy"});
    model.result("pg6").feature("arws1").set("arrowbase", "center");
    model.result("pg6").feature("arws1").set("color", "gray");
    model.result("pg6").feature("arws1").create("col1", "Color");
    model.result("pg6").feature("arws1").feature("col1").set("expr", "root.comp1.tcd.IsMag");
    model.result("pg6").create("line1", "Line");
    model.result("pg6").feature("line1").set("expr", new String[]{"abs(tcd.itot)"});
    model.result("pg6").feature("line1").set("linetype", "tube");
    model.result("pg6").feature("line1").set("inherittubescale", false);
    model.result("pg6").feature("line1").set("inheritplot", "arws1");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").setIndex("looplevel", 11, 0);
    model.result("pg7").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (tcd)");
    model.result("pg7").create("arws1", "ArrowSurface");
    model.result("pg7").feature("arws1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg7").feature("arws1").set("arrowbase", "center");
    model.result("pg7").feature("arws1").set("color", "gray");
    model.result("pg7").create("line1", "Line");
    model.result("pg7").feature("line1").set("expr", new String[]{"tcd.Evsref"});
    model.result("pg7").feature("line1").set("linetype", "tube");
    model.result("pg7").feature("line1").set("inherittubescale", false);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"tcd.Evsref"});
    model.result("pg7").feature("surf1").set("inheritplot", "line1");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset1");
    model.result("pg8").setIndex("looplevel", 11, 0);
    model.result("pg8").label("\u6d53\u5ea6 (tcd)");
    model.result("pg8").set("titletype", "custom");
    model.result("pg8").set("prefixintitle", "");
    model.result("pg8").set("expressionintitle", false);
    model.result("pg8").set("typeintitle", false);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"cBr2"});
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result("pg8").set("typeintitle", true);
    model.result("pg8").create("str1", "Streamline");
    model.result("pg8").feature("str1").set("expr", new String[]{"tcd.tflux_cBr2x", "tcd.tflux_cBr2y"});
    model.result("pg8").feature("str1").set("posmethod", "uniform");
    model.result("pg8").feature("str1").set("recover", "pprint");
    model.result("pg8").feature("str1").set("pointtype", "arrow");
    model.result("pg8").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg8").feature("str1").set("color", "gray");
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "dset1");
    model.result().numerical("gev2").set("expr", new String[]{"cBr2_tank"});
    model.result().numerical("gev2").set("descr", new String[]{"Tank and inlet concentration"});
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").set("data", "dset1");
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("expr", new String[]{"cBr2_tank"});
    model.result("pg9").feature("glob1").set("descr", new String[]{"Tank and inlet concentration"});
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg9").run();
    model.result("pg9").run();

    model.component("comp1").physics("tcd").feature("pce1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("Species", "Zn", 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("rhos", "rho_Zn", 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("Ms", "M_Zn", 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").setIndex("Vib", 1, 0, 0);

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("from", 1);
    model.component("comp1").func("step1").set("to", -1);
    model.component("comp1").func("step1").set("smooth", 1);

    model.component("comp1").physics("tcd").feature("ec1").set("Ias", "i_app*step1((t-t_charge)/1[s])");

    model.sol().remove("sol1");
    model.sol().remove("sol2");

    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result().remove("pg1");

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "A_cell", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m^2", 0);
    model.study("std1").feature("param").setIndex("pname", "A_cell", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m^2", 0);
    model.study("std1").feature("param").setIndex("pname", "i_app", 0);
    model.study("std1").feature("param").setIndex("plistarr", "10 20 40", 0);
    model.study("std1").feature("param").setIndex("punit", "mA/cm^2", 0);
    model.study("std1").feature("time").set("tlist", "range(0,0.025,1)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").create("st1", "StopCondition");
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "comp1.tcd.phis0_ec1<1.45[V]", 0);
    model.sol("sol1").feature("t1").feature("st1").set("stopcondwarn", false);

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");

    model.component("comp1").probe("var1").genResult("none");

    model.batch("p1").run("compute");

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"tcd.phis0_ec1"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u8fb9\u754c\u7535\u4f4d"});
    model.result("pg2").label("\u5bf9\u5730\u8fb9\u754c\u7535\u6781\u7535\u4f4d (tcd)");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 39, 0);
    model.result("pg3").setIndex("looplevel", 3, 1);
    model.result("pg3").label("\u7535\u89e3\u8d28\u7535\u4f4d (tcd)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"phil"});
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg3").feature("arws1").set("arrowbase", "center");
    model.result("pg3").feature("arws1").set("color", "gray");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 39, 0);
    model.result("pg4").setIndex("looplevel", 3, 1);
    model.result("pg4").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (tcd)");
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg4").feature("arws1").set("arrowbase", "center");
    model.result("pg4").feature("arws1").set("color", "gray");
    model.result("pg4").feature("arws1").create("col1", "Color");
    model.result("pg4").feature("arws1").feature("col1").set("expr", "root.comp1.tcd.IlMag");
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", new String[]{"abs(tcd.itot)"});
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("inherittubescale", false);
    model.result("pg4").feature("line1").set("inheritplot", "arws1");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 39, 0);
    model.result("pg5").setIndex("looplevel", 3, 1);
    model.result("pg5").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (tcd)");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"phis"});
    model.result("pg5").create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").set("expr", new String[]{"tcd.Isx", "tcd.Isy"});
    model.result("pg5").feature("arws1").set("arrowbase", "center");
    model.result("pg5").feature("arws1").set("color", "gray");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").setIndex("looplevel", 39, 0);
    model.result("pg6").setIndex("looplevel", 3, 1);
    model.result("pg6").label("\u7535\u6781\u7535\u6d41\u5bc6\u5ea6 (tcd)");
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("expr", new String[]{"tcd.Isx", "tcd.Isy"});
    model.result("pg6").feature("arws1").set("arrowbase", "center");
    model.result("pg6").feature("arws1").set("color", "gray");
    model.result("pg6").feature("arws1").create("col1", "Color");
    model.result("pg6").feature("arws1").feature("col1").set("expr", "root.comp1.tcd.IsMag");
    model.result("pg6").create("line1", "Line");
    model.result("pg6").feature("line1").set("expr", new String[]{"abs(tcd.itot)"});
    model.result("pg6").feature("line1").set("linetype", "tube");
    model.result("pg6").feature("line1").set("inherittubescale", false);
    model.result("pg6").feature("line1").set("inheritplot", "arws1");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevel", 39, 0);
    model.result("pg7").setIndex("looplevel", 3, 1);
    model.result("pg7").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (tcd)");
    model.result("pg7").create("arws1", "ArrowSurface");
    model.result("pg7").feature("arws1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg7").feature("arws1").set("arrowbase", "center");
    model.result("pg7").feature("arws1").set("color", "gray");
    model.result("pg7").create("line1", "Line");
    model.result("pg7").feature("line1").set("expr", new String[]{"tcd.Evsref"});

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg7").feature("line1").set("linetype", "tube");
    model.result("pg7").feature("line1").set("inherittubescale", false);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"tcd.Evsref"});
    model.result("pg7").feature("surf1").set("inheritplot", "line1");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").setIndex("looplevel", 39, 0);
    model.result("pg8").setIndex("looplevel", 3, 1);
    model.result("pg8").label("\u6d53\u5ea6 (tcd)");
    model.result("pg8").set("titletype", "custom");
    model.result("pg8").set("prefixintitle", "");
    model.result("pg8").set("expressionintitle", false);
    model.result("pg8").set("typeintitle", false);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"cBr2"});
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result("pg8").set("typeintitle", true);
    model.result("pg8").create("str1", "Streamline");
    model.result("pg8").feature("str1").set("expr", new String[]{"tcd.tflux_cBr2x", "tcd.tflux_cBr2y"});
    model.result("pg8").feature("str1").set("posmethod", "uniform");
    model.result("pg8").feature("str1").set("recover", "pprint");
    model.result("pg8").feature("str1").set("pointtype", "arrow");
    model.result("pg8").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg8").feature("str1").set("color", "gray");
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "dset3");
    model.result().numerical("gev2").set("expr", new String[]{"cBr2_tank"});
    model.result().numerical("gev2").set("descr", new String[]{"Tank and inlet concentration"});
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("expr", new String[]{"cBr2_tank"});
    model.result("pg9").feature("glob1").set("descr", new String[]{"Tank and inlet concentration"});
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u6c60\u7535\u538b vs. \u65f6\u95f4");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u7535\u6c60\u7535\u538b (V)");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("legendmethod", "evaluated");
    model.result("pg2").feature("glob1").set("legendpattern", "i<sub>app</sub>=eval(i_app/10) mA/cm<sup>2</sup>");
    model.result("pg2").run();
    model.result("pg8").run();
    model.result("pg8").label("\u6eb4\u6d53\u5ea6");
    model.result("pg8").set("typeintitle", false);
    model.result("pg8").run();
    model.result("pg8").feature("str1").set("titletype", "none");
    model.result("pg8").feature("str1").set("posmethod", "selection");
    model.result("pg8").feature("str1").selection().named("sel4");
    model.result("pg8").feature("str1").set("selnumber", 5);
    model.result("pg8").run();
    model.result("pg8").set("looplevel", new int[]{21, 3});
    model.result("pg8").run();
    model.result("pg8").set("looplevel", new int[]{39, 3});
    model.result("pg8").run();
    model.result("pg9").run();
    model.result("pg9").label("\u69fd\u6d53\u5ea6");
    model.result("pg9").run();
    model.result("pg9").feature("glob1").set("legendmethod", "evaluated");
    model.result("pg9").feature("glob1").set("legendpattern", "i<sub>app</sub>=eval(i_app/10) mA/cm<sup>2</sup>");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").set("titletype", "none");
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").run();
    model.result("pg10").label("\u6c89\u79ef\u950c\u7684\u4f53\u79ef\u5206\u6570");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", "tcd.deltaeps_pce1_Zn");
    model.result("pg10").feature("surf1").set("descr", "\u4f53\u79ef\u5206\u6570\u53d8\u5316");
    model.result("pg10").run();
    model.result("pg10").set("looplevel", new int[]{21});
    model.result("pg10").set("legendactive", true);
    model.result("pg10").set("legendnotation", "engineering");
    model.result("pg10").run();

    model.title("\u950c-\u6eb4\u6c27\u5316\u8fd8\u539f\u6db2\u6d41\u7535\u6c60");

    model
         .description("\u950c-\u6eb4\u6c27\u5316\u8fd8\u539f\u6db2\u6d41\u7535\u6c60\u662f\u4e00\u79cd\u9002\u7528\u4e8e\u56fa\u5b9a\u5e94\u7528\u7684\u7535\u5316\u5b66\u50a8\u80fd\u6280\u672f\u3002\n\n\u4e0e\u5176\u4ed6\u6db2\u6d41\u7535\u6c60\u5316\u5b66\u4f53\u7cfb\u76f8\u6bd4\uff0c\u950c-\u6eb4\u7535\u6c60\u5177\u6709\u6210\u672c\u4f4e\u3001\u80fd\u91cf\u5bc6\u5ea6\u5927\u548c\u80fd\u6e90\u6548\u7387\u9ad8\u7b49\u7279\u70b9\u3002\n\n\u5728\u7535\u6c60\u5145\u7535\u8fc7\u7a0b\u4e2d\uff0c\u950c\u91d1\u5c5e\u4f1a\u5728\u8d1f\u6781\u6c89\u79ef\uff0c\u800c\u6b63\u6781\u4f1a\u4ea7\u751f\u6eb4\u3002\n\n\u672c\u6559\u7a0b\u6a21\u62df\u7535\u6c60\u5728\u5145\u653e\u7535\u5faa\u73af\u671f\u95f4\u7684\u7535\u6c60\u7535\u538b\uff0c\u4ee5\u53ca\u6eb4\u548c\u950c\u7684\u4ea7\u751f-\u6d88\u8017\u60c5\u51b5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("znbr_flow_battery.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
