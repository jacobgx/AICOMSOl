/*
 * cu_electrowinning_bubbly_flow.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:57 by COMSOL 6.3.0.290. */
public class cu_electrowinning_bubbly_flow {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrodeposition_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", "geom1");
    model.component("comp1").physics("tcd").field("concentration").field("cCu");
    model.component("comp1").physics("tcd").field("concentration").component(new String[]{"cCu", "cH", "cHSO4"});
    model.component("comp1").physics().create("bf", "LaminarBubblyFlow", "geom1");

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
    model.study("std1").feature("cdi").setSolveFor("/physics/bf", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/tcd", true);
    model.study("std1").feature("time").setSolveFor("/physics/bf", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.02467, 0.17});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{0.025, 0.01});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{0.02467, 0});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 0.032, 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 0.01, 1);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("pt2", "Point");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", 0.02467, 0);
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", 0.015, 1);
    model.component("comp1").geom("geom1").run("pt2");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("c0Cu", "630 [mol/m^3]", "Cu \u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("c0H", "1837 [mol/m^3]", "H \u7684\u521d\u59cb\u6d53\u5ea6");
    model.param()
         .set("DCu", "10^(-0.676-0.481*log10(Mw_H2SO4*cH2SO4*1[m^3/kg])-0.156*log10(Mw_Cu*c0Cu*1[m^3/kg])+0.9885*(-8340.61/(8.314*T*1[1/K])))*1e-4[m^2/s]", "Cu \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DH", "5.3e-9[m^2/s]", "H \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DHSO4", "7.6e-10[m^2/s]", "HSO4 \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("Mw_H2SO4", "0.098 [kg/mol]", "H2SO4 \u7684\u5206\u5b50\u91cf");
    model.param().set("cH2SO4", "1836.73 [mol/m^3]", "H2SO4 \u7684\u6d53\u5ea6");
    model.param().set("T", "313 [K]", "\u6e29\u5ea6");
    model.param().set("Itot", "5.14 [A]", "\u5916\u52a0\u7535\u6d41");
    model.param().set("Eeq0_O2", "1.23 [V]", "\u6807\u51c6\u5e73\u8861\u7535\u4f4d O2");
    model.param().set("i0a_O2", "3e-7 [A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u9633\u6781");
    model.param().set("Eeq0_Cu", "0.34 [V]", "\u6807\u51c6\u5e73\u8861\u7535\u4f4d Cu");
    model.param().set("i0c_Cu", "100 [A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u9634\u6781");
    model.param().set("alphaa_Cu", "1.455", "\u9633\u6781\u4f20\u9012\u7cfb\u6570");
    model.param().set("rho_Cu", "8960 [kg/m^3]", "Cu \u7684\u5bc6\u5ea6");
    model.param().set("Mw_Cu", "0.06355 [kg/mol]", "Cu \u7684\u5206\u5b50\u91cf");
    model.param().set("rho", "1200 [kg/m^3]", "\u7535\u89e3\u8d28\u5bc6\u5ea6");
    model.param().set("nu", "0.835e-3 [kg/m/s]", "\u7535\u89e3\u8d28\u9ecf\u5ea6");
    model.param().set("Mw_O2", "0.032 [kg/mol]", "O2 \u7684\u5206\u5b50\u91cf");
    model.param().set("d_b", "50e-6 [m]", "\u6c14\u6ce1\u76f4\u5f84");
    model.param().set("Vb", "0.0001 [m/s]", "\u5165\u53e3\u901f\u5ea6");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("tcd").prop("SpeciesProperties").set("FromElectroneutrality", 3);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", 2, 0);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", 1, 1);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", -1, 2);
    model.component("comp1").physics("tcd").create("sep1", "Separator", 2);
    model.component("comp1").physics("tcd").feature("sep1").selection().all();
    model.component("comp1").physics("tcd").feature("sep1").set("u", new String[]{"bf.phil*u", "bf.phil*v", "0"});
    model.component("comp1").physics("tcd").feature("sep1")
         .set("D_cCu", new String[]{"DCu", "0", "0", "0", "DCu", "0", "0", "0", "DCu"});
    model.component("comp1").physics("tcd").feature("sep1")
         .set("D_cH", new String[]{"DH", "0", "0", "0", "DH", "0", "0", "0", "DH"});
    model.component("comp1").physics("tcd").feature("sep1")
         .set("D_cHSO4", new String[]{"DHSO4", "0", "0", "0", "DHSO4", "0", "0", "0", "DHSO4"});
    model.component("comp1").physics("tcd").feature("sep1").set("epsl", "bf.phil");
    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 1);
    model.component("comp1").physics("tcd").feature("es1").label("\u9633\u6781\u8868\u9762");
    model.component("comp1").physics("tcd").feature("es1").selection().set(8);
    model.component("comp1").physics("tcd").feature("es1").set("BoundaryCondition", "TotalCurrent");
    model.component("comp1").physics("tcd").feature("es1").set("Itl", "Itot");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").label("\u6790\u6c27\u53cd\u5e94");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("nm", 4);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", -4, 1);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("Eeq_ref", "Eeq0_O2");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("i0_ref", "i0a_O2");
    model.component("comp1").physics("tcd").create("es2", "ElectrodeSurface", 1);
    model.component("comp1").physics("tcd").feature("es2").selection().set(1);
    model.component("comp1").physics("tcd").feature("es2").label("\u9634\u6781\u8868\u9762");
    model.component("comp1").physics("tcd").feature("es2").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("Species", "Cu", 0, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("rhos", "rho_Cu", 0, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("Ms", "Mw_Cu", 0, 0);
    model.component("comp1").physics("tcd").feature("es2").feature("er1").label("\u94dc\u6c89\u79ef\u53cd\u5e94");
    model.component("comp1").physics("tcd").feature("es2").feature("er1").set("nm", 2);
    model.component("comp1").physics("tcd").feature("es2").feature("er1").setIndex("Vi0", -1, 0);
    model.component("comp1").physics("tcd").feature("es2").feature("er1").setIndex("Vib", 1, 0, 0);
    model.component("comp1").physics("tcd").feature("es2").feature("er1").set("Eeq_ref", "Eeq0_Cu");
    model.component("comp1").physics("tcd").feature("es2").feature("er1").set("i0_ref", "i0c_Cu");
    model.component("comp1").physics("tcd").feature("es2").feature("er1").set("alphaa", "alphaa_Cu");
    model.component("comp1").physics("tcd").create("in1", "Inflow", 1);
    model.component("comp1").physics("tcd").feature("in1").selection().set(3);
    model.component("comp1").physics("tcd").feature("in1").setIndex("c0", "c0Cu", 0);
    model.component("comp1").physics("tcd").feature("in1").setIndex("c0", "c0H", 1);
    model.component("comp1").physics("tcd").create("out1", "Outflow", 1);
    model.component("comp1").physics("tcd").feature("out1").selection().set(9);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c0Cu", 0);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c0H", 1);
    model.component("comp1").physics("bf").prop("PhysicalModelProperty").set("LowGasConcentration", false);
    model.component("comp1").physics("bf").feature("fp1").set("rhol_mat", "userdef");
    model.component("comp1").physics("bf").feature("fp1").set("rhol", "rho");
    model.component("comp1").physics("bf").feature("fp1").set("mul_mat", "userdef");
    model.component("comp1").physics("bf").feature("fp1").set("mul", "nu");
    model.component("comp1").physics("bf").feature("fp1").set("rhog_mat", "CalculateFromIdealGasLaw");
    model.component("comp1").physics("bf").feature("fp1").set("Mg", "Mw_O2");
    model.component("comp1").physics("bf").feature("fp1").set("diamb", "d_b");
    model.component("comp1").physics("bf").feature("fp1").set("SlipModel", "PressureDragBalance");
    model.component("comp1").physics("bf").feature("init1").set("p", "rho*g_const*(0.17-y)");
    model.component("comp1").physics("bf").create("gr1", "Gravity", 2);
    model.component("comp1").physics("bf").feature("gr1").selection().all();
    model.component("comp1").physics("bf").create("wallbc2", "WallBC", 1);
    model.component("comp1").physics("bf").feature("wallbc2").label("\u58c1\uff08\u8fdb\u6c14\u53e3\uff09");
    model.component("comp1").physics("bf").feature("wallbc2").selection().set(8);
    model.component("comp1").physics("bf").feature("wallbc2").set("GasBoundaryCondition", "GasFlux");
    model.component("comp1").physics("bf").feature("wallbc2").set("Nrhogeff", "(tcd.iloc_er1)*Mw_O2/(4*F_const)");
    model.component("comp1").physics("bf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("bf").feature("inl1")
         .label("\u6db2\u4f53\u5165\u53e3\u548c\u6c14\u4f53\u51fa\u53e3");
    model.component("comp1").physics("bf").feature("inl1").selection().set(3);
    model.component("comp1").physics("bf").feature("inl1").set("U0in", "Vb");
    model.component("comp1").physics("bf").feature("inl1").set("GasBoundaryCondition", "GasOutlet");
    model.component("comp1").physics("bf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("bf").feature("out1").selection().set(9);

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(1);
    model.component("comp1").mesh("mesh1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("size2").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("size2").selection().set(6);
    model.component("comp1").mesh("mesh1").feature("size2").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size2").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature().move("size2", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").selection().set(1, 2, 5, 6, 7, 10);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blnlayers", 4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blhmin", "0.00005");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("cdi2", "CurrentDistributionInitialization");
    model.study("std1").feature("cdi2").set("initType", "secondary");
    model.study("std1").feature().move("cdi2", 1);
    model.study("std1").feature("time").set("tlist", "range(0,0.25,60)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{""});
    model.result("pg1").feature("glob1").set("expr", new String[]{"tcd.phisext_es1"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u5916\u90e8\u7535\u4f4d"});
    model.result("pg1").label("\u5bf9\u5730\u8fb9\u754c\u7535\u6781\u7535\u4f4d (tcd)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 241, 0);
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u4f4d (tcd)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"phil"});
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("recover", "pprint");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 241, 0);
    model.result("pg3").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (tcd)");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("recover", "pprint");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").feature("str1").create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("expr", "root.comp1.tcd.IlMag");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", new String[]{"abs(tcd.itot)"});
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("inherittubescale", false);
    model.result("pg3").feature("line1").set("inheritplot", "str1");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 241, 0);
    model.result("pg4").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (tcd)");
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", new String[]{"tcd.phisext"});
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("inherittubescale", false);
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 241, 0);
    model.result("pg5").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (tcd)");
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("recover", "pprint");
    model.result("pg5").feature("str1").set("pointtype", "arrow");
    model.result("pg5").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("str1").set("color", "gray");
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("expr", new String[]{"tcd.Evsref"});
    model.result("pg5").feature("line1").set("linetype", "tube");
    model.result("pg5").feature("line1").set("inherittubescale", false);
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 241, 0);
    model.result("pg6").label("\u7535\u6781\u603b\u539a\u5ea6\u53d8\u5316 (tcd)");
    model.result("pg6").create("line1", "Line");
    model.result("pg6").feature("line1").set("expr", new String[]{"tcd.sbtot"});
    model.result("pg6").feature("line1").set("linetype", "tube");
    model.result("pg6").feature("line1").set("inherittubescale", false);
    model.result("pg6").feature("line1").set("unit", "\u00b5m");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").setIndex("looplevel", 241, 0);
    model.result("pg7").label("\u6d53\u5ea6, Cu (tcd)");
    model.result("pg7").set("titletype", "custom");
    model.result("pg7").set("prefixintitle", "");
    model.result("pg7").set("expressionintitle", false);
    model.result("pg7").set("typeintitle", false);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"cCu"});
    model.result("pg7").feature("surf1").set("colortable", "Rainbow");
    model.result("pg7").set("typeintitle", true);
    model.result("pg7").create("str1", "Streamline");
    model.result("pg7").feature("str1").set("expr", new String[]{"tcd.tflux_cCux", "tcd.tflux_cCuy"});
    model.result("pg7").feature("str1").set("posmethod", "uniform");
    model.result("pg7").feature("str1").set("recover", "pprint");
    model.result("pg7").feature("str1").set("pointtype", "arrow");
    model.result("pg7").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg7").feature("str1").set("color", "gray");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset1");
    model.result("pg8").setIndex("looplevel", 241, 0);
    model.result("pg8").label("\u6d53\u5ea6, H (tcd)");
    model.result("pg8").set("titletype", "custom");
    model.result("pg8").set("prefixintitle", "");
    model.result("pg8").set("expressionintitle", false);
    model.result("pg8").set("typeintitle", false);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"cH"});
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result("pg8").set("typeintitle", true);
    model.result("pg8").create("str1", "Streamline");
    model.result("pg8").feature("str1").set("expr", new String[]{"tcd.tflux_cHx", "tcd.tflux_cHy"});
    model.result("pg8").feature("str1").set("posmethod", "uniform");
    model.result("pg8").feature("str1").set("recover", "pprint");
    model.result("pg8").feature("str1").set("pointtype", "arrow");
    model.result("pg8").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg8").feature("str1").set("color", "gray");
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").set("data", "dset1");
    model.result("pg9").setIndex("looplevel", 241, 0);
    model.result("pg9").label("\u6d53\u5ea6, HSO4 (tcd)");
    model.result("pg9").set("titletype", "custom");
    model.result("pg9").set("prefixintitle", "");
    model.result("pg9").set("expressionintitle", false);
    model.result("pg9").set("typeintitle", false);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"cHSO4"});
    model.result("pg9").feature("surf1").set("colortable", "Rainbow");
    model.result("pg9").set("typeintitle", true);
    model.result("pg9").create("str1", "Streamline");
    model.result("pg9").feature("str1").set("expr", new String[]{"tcd.tflux_cHSO4x", "tcd.tflux_cHSO4y"});
    model.result("pg9").feature("str1").set("posmethod", "uniform");
    model.result("pg9").feature("str1").set("recover", "pprint");
    model.result("pg9").feature("str1").set("pointtype", "arrow");
    model.result("pg9").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg9").feature("str1").set("color", "gray");
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").label("\u6db2\u4f53 (bf)");
    model.result("pg10").set("frametype", "spatial");
    model.result("pg10").feature().create("surf1", "Surface");
    model.result("pg10").feature("surf1").label("\u901f\u5ea6\u5927\u5c0f");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("expr", "bf.Ul");
    model.result("pg10").feature("surf1").set("smooth", "internal");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("data", "parent");
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").label("\u6c14\u76f8 (bf)");
    model.result("pg11").set("frametype", "spatial");
    model.result("pg11").feature().create("surf1", "Surface");
    model.result("pg11").feature("surf1").label("\u4f53\u79ef\u5206\u6570\uff0c\u6c14\u76f8");
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("expr", "bf.phig");
    model.result("pg11").feature("surf1").set("smooth", "internal");
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("data", "parent");
    model.result().create("pg12", "PlotGroup2D");
    model.result("pg12").label("\u538b\u529b (bf)");
    model.result("pg12").set("frametype", "spatial");
    model.result("pg12").feature().create("con1", "Contour");
    model.result("pg12").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg12").feature("con1").set("showsolutionparams", "on");
    model.result("pg12").feature("con1").set("expr", "p");
    model.result("pg12").feature("con1").set("number", 40);
    model.result("pg12").feature("con1").set("levelrounding", false);
    model.result("pg12").feature("con1").set("smooth", "internal");
    model.result("pg12").feature("con1").set("showsolutionparams", "on");
    model.result("pg12").feature("con1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg10").run();
    model.result("pg10").create("arws1", "ArrowSurface");
    model.result("pg10").feature("arws1").set("expr", new String[]{"u", "v"});
    model.result("pg10").feature("arws1").set("descr", "\u901f\u5ea6\u573a\uff0c\u6db2\u76f8");
    model.result("pg10").feature("arws1").set("color", "black");
    model.result("pg10").run();
    model.result("pg11").run();
    model.result("pg11").create("str1", "Streamline");
    model.result("pg11").feature("str1").set("expr", new String[]{"u", "v"});
    model.result("pg11").feature("str1").set("descr", "\u901f\u5ea6\u573a\uff0c\u6db2\u76f8");
    model.result("pg11").feature("str1").set("posmethod", "uniform");
    model.result("pg11").feature("str1").set("udist", 0.02);
    model.result("pg11").feature("str1").set("pointtype", "arrow");
    model.result("pg11").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg11").feature("str1").set("color", "white");
    model.result("pg11").run();
    model.result("pg7").run();
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").run();
    model.result("pg13").label("\u6cbf\u9634\u6781\u8868\u9762\u65b9\u5411\u7684\u94dc\u6d53\u5ea6");
    model.result("pg13").setIndex("looplevelinput", "last", 0);
    model.result("pg13").create("lngr1", "LineGraph");
    model.result("pg13").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg13").feature("lngr1").set("linewidth", "preference");
    model.result("pg13").feature("lngr1").selection().set(1);
    model.result("pg13").feature("lngr1").set("expr", "cCu");
    model.result("pg13").feature("lngr1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0ccCu");
    model.result("pg13").feature("lngr1").set("xdata", "expr");
    model.result("pg13").feature("lngr1").set("xdataexpr", "y");
    model.result("pg13").run();
    model.result("pg13").feature("lngr1").set("xdatadescractive", true);
    model.result("pg13").feature("lngr1")
         .set("xdatadescr", "\u8ddd\u9634\u6781\u8868\u9762\u5e95\u90e8\u7684\u8ddd\u79bb");
    model.result("pg13").run();
    model.result().dataset().create("cpt1", "CutPoint2D");
    model.result().dataset("cpt1").set("pointx", "0 0 0");
    model.result().dataset("cpt1").set("pointy", "0.05 0.15 0.16");
    model.result().create("pg14", "PlotGroup1D");
    model.result("pg14").run();
    model.result("pg14").label("\u94dc\u6d53\u5ea6\uff08\u70b9\u7ed3\u679c\u56fe\uff09");
    model.result("pg14").create("ptgr1", "PointGraph");
    model.result("pg14").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg14").feature("ptgr1").set("linewidth", "preference");
    model.result("pg14").feature("ptgr1").set("data", "cpt1");
    model.result("pg14").feature("ptgr1").set("expr", "cCu");
    model.result("pg14").feature("ptgr1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0ccCu");
    model.result("pg14").run();
    model.result("pg14").feature("ptgr1").set("legend", true);
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", 0.1, 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", 0.1, 1, 1);
    model.result().dataset("cln1").setIndex("genpoints", 0.001, 1, 0);
    model.result().create("pg15", "PlotGroup1D");
    model.result("pg15").run();
    model.result("pg15").label("\u94dc\u6d53\u5ea6\uff08\u8fb9\u754c\u5c42\uff09");
    model.result("pg15").create("lngr1", "LineGraph");
    model.result("pg15").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg15").feature("lngr1").set("linewidth", "preference");
    model.result("pg15").feature("lngr1").set("data", "cln1");
    model.result("pg15").feature("lngr1").setIndex("looplevelinput", "last", 0);
    model.result("pg15").feature("lngr1").set("expr", "cCu");
    model.result("pg15").feature("lngr1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0ccCu");
    model.result("pg15").feature("lngr1").set("xdata", "expr");
    model.result("pg15").feature("lngr1").set("xdataexpr", "x");
    model.result("pg15").feature("lngr1").set("xdatadescractive", true);
    model.result("pg15").feature("lngr1").set("xdatadescr", "\u8ddd\u9634\u6781\u8868\u9762\u7684\u8ddd\u79bb");
    model.result("pg15").run();
    model.result().create("pg16", "PlotGroup1D");
    model.result("pg16").run();
    model.result("pg16").label("\u94dc\u6c89\u79ef\u539a\u5ea6");
    model.result("pg16").setIndex("looplevelinput", "last", 0);
    model.result("pg16").create("lngr1", "LineGraph");
    model.result("pg16").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg16").feature("lngr1").set("linewidth", "preference");
    model.result("pg16").feature("lngr1").selection().set(1);
    model.result("pg16").feature("lngr1").set("expr", "tcd.sbtot");
    model.result("pg16").feature("lngr1").set("descr", "\u7535\u6781\u603b\u539a\u5ea6\u53d8\u5316");
    model.result("pg16").feature("lngr1").set("unit", "\u00b5m");
    model.result("pg16").feature("lngr1").set("xdata", "expr");
    model.result("pg16").feature("lngr1").set("xdataexpr", "y");
    model.result("pg16").feature("lngr1").set("xdatadescractive", true);
    model.result("pg16").feature("lngr1")
         .set("xdatadescr", "\u8ddd\u9634\u6781\u8868\u9762\u5e95\u90e8\u7684\u8ddd\u79bb");
    model.result("pg16").run();

    model
         .title("\u4f7f\u7528\u6c14\u6ce1\u6d41\u5efa\u7acb\u94dc\u7535\u89e3\u6c89\u79ef\u4e24\u76f8\u6d41\u6a21\u578b");

    model
         .description("\u94dc\u7535\u89e3\u6c89\u79ef\u662f\u901a\u8fc7\u4f7f\u5916\u90e8\u7535\u6d41\u6d41\u7ecf\u7535\u89e3\u69fd\uff0c\u4f7f\u7528\u4e0d\u6eb6\u6027\u9633\u6781\u4ece\u7535\u89e3\u8d28\u6eb6\u6db2\u4e2d\u63d0\u53d6\u94dc\u5e76\u5c06\u5176\u6c89\u79ef\u5728\u9634\u6781\u8868\u9762\u7684\u8fc7\u7a0b\u3002\u5728\u6b64\u8fc7\u7a0b\u4e2d\uff0c\u9633\u6781\u8868\u9762\u4f1a\u4ea7\u751f\u6c27\u6c14\u6c14\u6ce1\uff0c\u5bfc\u81f4\u9633\u6781\u548c\u9634\u6781\u8868\u9762\u4e4b\u95f4\u5f62\u6210\u8f83\u5927\u7684\u56de\u6d41\u533a\uff0c\u8fd9\u9700\u8981\u8fdb\u884c\u4e24\u76f8\u6d41\u5efa\u6a21\u3002\n\n\u5728\u6b64\u6a21\u578b\u4e2d\uff0c\u6211\u4eec\u4f7f\u7528\u201c\u4e09\u6b21\u7535\u6d41\u5206\u5e03\uff0cNernst-Planck\u201d\u63a5\u53e3\u6a21\u62df\u79bb\u5b50\u7269\u8d28\u7684\u7535\u8377\u548c\u8d28\u91cf\u4f20\u9012\uff0c\u5e76\u4f7f\u7528\u201c\u6c14\u6ce1\u6d41\uff0c\u5c42\u6d41\u201d\u63a5\u53e3\u6a21\u62df\u7531\u4e8e\u9633\u6781\u8868\u9762\u6790\u51fa\u6c27\u6c14\u800c\u4ea7\u751f\u7684\u4e24\u76f8\u6d41\u3002\u5176\u4e2d\u6f14\u793a\u4e86\u4e24\u76f8\u6d41\u5bf9\u7535\u9540\u94dc\u7684\u5f71\u54cd\u3002\n\n\u6b64\u6a21\u578b\u9700\u8981\u201c\u7535\u9540\u6a21\u5757\u201d\u548c\u201cCFD \u6a21\u5757\u201d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("cu_electrowinning_bubbly_flow.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
