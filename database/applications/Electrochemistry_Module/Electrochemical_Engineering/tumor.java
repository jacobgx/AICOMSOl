/*
 * tumor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:54 by COMSOL 6.3.0.290. */
public class tumor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrochemistry_Module\\Electrochemical_Engineering");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", "geom1");
    model.component("comp1").physics("tcd").field("concentration").field("Na");
    model.component("comp1").physics("tcd").field("concentration").component(new String[]{"Na", "H", "Cl"});

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/tcd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("r_a", "1[mm]", "\u9633\u6781\u534a\u5f84");
    model.param().set("D_Na", "1.33e-9[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0cNa");
    model.param().set("D_H", "9.31e-9[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0cH");
    model.param().set("D_Cl", "2.03e-9[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0cCl");
    model.param().set("T", "298[K]", "\u6e29\u5ea6");
    model.param().set("Na0", "0.16[mol/liter]", "\u521d\u59cb\u6d53\u5ea6\uff0cNa");
    model.param().set("H0", "1e-7[mol/liter]", "\u521d\u59cb\u6d53\u5ea6\uff0cH");
    model.param().set("Cl0", "0.16[mol/liter]", "\u521d\u59cb\u6d53\u5ea6\uff0cCl");
    model.param().set("z_Na", "1[1]", "\u7535\u8377\u6570\uff0cNa");
    model.param().set("z_H", "1[1]", "\u7535\u8377\u6570\uff0cH");
    model.param().set("z_Cl", "-1[1]", "\u7535\u8377\u6570\uff0cCl");
    model.param().set("j_tot0", "100[A/m^2]", "\u521d\u59cb\u603b\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("j_I0", "1e-6[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u53cd\u5e94 I");
    model.param().set("j_II0", "10[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u53cd\u5e94 II");
    model.param().set("V_ra0", "-1.4787[V]", "\u521d\u59cb\u9633\u6781\u7535\u4f4d");
    model.param().set("E_eqI", "1.23[V]", "\u5e73\u8861\u7535\u4f4d\uff0c\u53cd\u5e94 I");
    model.param().set("E_eqII", "1.36[V]", "\u5e73\u8861\u7535\u4f4d\uff0c\u53cd\u5e94 II");
    model.param()
         .set("kappa0", "F_const^2*(D_Na*Na0+D_H*H0+D_Cl*Cl0)/(R_const*T)", "\u521d\u59cb\u7535\u5bfc\u7387");
    model.param().set("r_ext", "60[mm]", "\u5916\u90e8\u8fb9\u754c\u534a\u5f84");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("E_cell", "(0.4977+0.2567*log(100+t/1[s]))[V]", "\u5355\u5143\u7535\u538b");
    model.component("comp1").variable("var1").set("pH", "-log10(H/1[mol/liter])", "pH \u8868\u8fbe\u5f0f");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "r_a", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "r_ext", 1);
    model.component("comp1").geom("geom1").run("i1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "z_Na", 0);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "z_H", 1);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "z_Cl", 2);
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_Na", new String[]{"D_Na", "0", "0", "0", "D_Na", "0", "0", "0", "D_Na"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_H", new String[]{"D_H", "0", "0", "0", "D_H", "0", "0", "0", "D_H"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_Cl", new String[]{"D_Cl", "0", "0", "0", "D_Cl", "0", "0", "0", "D_Cl"});
    model.component("comp1").physics("tcd").create("eip1", "ElectrolytePotential", 0);
    model.component("comp1").physics("tcd").feature("eip1").selection().set(2);
    model.component("comp1").physics("tcd").create("conc1", "Concentration", 0);
    model.component("comp1").physics("tcd").feature("conc1").selection().set(2);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 1);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "H0", 1);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 2);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "Cl0", 2);
    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 0);
    model.component("comp1").physics("tcd").feature("es1").selection().set(1);
    model.component("comp1").physics("tcd").feature("es1").set("phisext0", "E_cell");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", -1, 1);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("Eeq_ref", "E_eqI");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("cref", "H0", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("i0_ref", "j_I0");
    model.component("comp1").physics("tcd").feature("es1").create("er2", "ElectrodeReaction", 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er2").setIndex("Vi0", 1, 2);
    model.component("comp1").physics("tcd").feature("es1").feature("er2").set("Eeq_ref", "E_eqII");
    model.component("comp1").physics("tcd").feature("es1").feature("er2").setIndex("cref", "Cl0", 2, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er2").set("i0_ref", "j_II0");
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "H0", 1);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "Cl0", 2);

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", "1e-8");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hgrad", 1.1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "0 3600");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tout", "tsteps");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (tcd)");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "r");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", new String[]{"phil"});
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28");
    model.result("pg2").label("\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28 (tcd)");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "r");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature("lngr1").set("expr", new String[]{"Na"});
    model.result("pg2").feature("lngr1").label("\u7269\u8d28 Na");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("autosolution", false);
    model.result("pg2").feature("lngr1").set("autoexpr", false);
    model.result("pg2").feature("lngr1").set("autodescr", false);
    model.result("pg2").feature("lngr1").set("legendprefix", "Na ");
    model.result("pg2").feature("lngr1").set("descractive", true);
    model.result("pg2").feature("lngr1").set("descr", "\u6d53\u5ea6");
    model.result("pg2").create("lngr2", "LineGraph");
    model.result("pg2").feature("lngr2").set("xdata", "expr");
    model.result("pg2").feature("lngr2").set("xdataexpr", "r");
    model.result("pg2").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr2").selection().set(1);
    model.result("pg2").feature("lngr2").set("expr", new String[]{"H"});
    model.result("pg2").feature("lngr2").label("\u7269\u8d28 H");
    model.result("pg2").feature("lngr2").set("legend", true);
    model.result("pg2").feature("lngr2").set("autosolution", false);
    model.result("pg2").feature("lngr2").set("autoexpr", false);
    model.result("pg2").feature("lngr2").set("autodescr", false);
    model.result("pg2").feature("lngr2").set("legendprefix", "H ");
    model.result("pg2").feature("lngr2").set("descractive", true);
    model.result("pg2").feature("lngr2").set("descr", "\u6d53\u5ea6");
    model.result("pg2").create("lngr3", "LineGraph");
    model.result("pg2").feature("lngr3").set("xdata", "expr");
    model.result("pg2").feature("lngr3").set("xdataexpr", "r");
    model.result("pg2").feature("lngr3").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr3").selection().set(1);
    model.result("pg2").feature("lngr3").set("expr", new String[]{"Cl"});
    model.result("pg2").feature("lngr3").label("\u7269\u8d28 Cl");
    model.result("pg2").feature("lngr3").set("legend", true);
    model.result("pg2").feature("lngr3").set("autosolution", false);
    model.result("pg2").feature("lngr3").set("autoexpr", false);
    model.result("pg2").feature("lngr3").set("autodescr", false);
    model.result("pg2").feature("lngr3").set("legendprefix", "Cl ");
    model.result("pg2").feature("lngr3").set("descractive", true);
    model.result("pg2").feature("lngr3").set("descr", "\u6d53\u5ea6");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u6d53\u5ea6, Na (tcd)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "r");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"Na"});
    model.result().dataset().create("rev1", "Revolve1D");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", true);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "rev1");
    model.result("pg4").setIndex("looplevel", 96, 0);
    model.result("pg4").label("\u6d53\u5ea6, Na, 2D (tcd)");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"Na"});
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("expr", new String[]{"tcd.tflux_Nar", "tcd.tflux_Naz"});
    model.result("pg4").feature("arws1").set("xnumber", 10);
    model.result("pg4").feature("arws1").set("ynumber", 10);
    model.result("pg4").feature("arws1").set("color", "black");
    model.result("pg4").feature("arws1").set("revcoordsys", "cylindrical");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("typeintitle", true);
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").label("\u6d53\u5ea6, H (tcd)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "r");
    model.result("pg5").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr1").selection().set(1);
    model.result("pg5").feature("lngr1").set("expr", new String[]{"H"});
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "rev1");
    model.result("pg6").setIndex("looplevel", 96, 0);
    model.result("pg6").label("\u6d53\u5ea6, H, 2D (tcd)");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"H"});
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("expr", new String[]{"tcd.tflux_Hr", "tcd.tflux_Hz"});
    model.result("pg6").feature("arws1").set("xnumber", 10);
    model.result("pg6").feature("arws1").set("ynumber", 10);
    model.result("pg6").feature("arws1").set("color", "black");
    model.result("pg6").feature("arws1").set("revcoordsys", "cylindrical");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("typeintitle", true);
    model.result("pg6").set("prefixintitle", "");
    model.result("pg6").set("expressionintitle", false);
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").label("\u6d53\u5ea6, Cl (tcd)");
    model.result("pg7").set("titletype", "custom");
    model.result("pg7").set("prefixintitle", "");
    model.result("pg7").set("expressionintitle", false);
    model.result("pg7").set("typeintitle", false);
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "r");
    model.result("pg7").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg7").feature("lngr1").selection().set(1);
    model.result("pg7").feature("lngr1").set("expr", new String[]{"Cl"});
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "rev1");
    model.result("pg8").setIndex("looplevel", 96, 0);
    model.result("pg8").label("\u6d53\u5ea6, Cl, 2D (tcd)");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"Cl"});
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result("pg8").create("arws1", "ArrowSurface");
    model.result("pg8").feature("arws1").set("expr", new String[]{"tcd.tflux_Clr", "tcd.tflux_Clz"});
    model.result("pg8").feature("arws1").set("xnumber", 10);
    model.result("pg8").feature("arws1").set("ynumber", 10);
    model.result("pg8").feature("arws1").set("color", "black");
    model.result("pg8").feature("arws1").set("revcoordsys", "cylindrical");
    model.result("pg8").set("titletype", "custom");
    model.result("pg8").set("typeintitle", true);
    model.result("pg8").set("prefixintitle", "");
    model.result("pg8").set("expressionintitle", false);
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("colortable", "Amber");
    model.result("pg4").run();
    model.result("pg4").feature("arws1").set("xnumber", 13);
    model.result("pg4").feature("arws1").set("ynumber", 13);
    model.result("pg4").feature("arws1").set("color", "white");
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevelinput", "interp", 0);
    model.result("pg5").setIndex("interp", "range(0,600,3600)", 0);
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("linemarker", "cycle");
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("colortable", "Amethyst");
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").setIndex("looplevelinput", "interp", 0);
    model.result("pg7").setIndex("interp", "range(0,600,3600)", 0);
    model.result("pg7").set("legendpos", "lowerright");
    model.result("pg7").run();
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("linemarker", "cycle");
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("colortable", "Lagoon");
    model.result("pg8").run();
    model.result("pg8").feature("arws1").set("xnumber", 13);
    model.result("pg8").feature("arws1").set("ynumber", 13);
    model.result("pg8").feature("arws1").set("color", "white");
    model.result("pg5").run();
    model.result().duplicate("pg9", "pg5");
    model.result("pg9").run();
    model.result("pg9").label("pH \u503c");
    model.result("pg9").set("legendpos", "lowerright");
    model.result("pg9").run();
    model.result("pg9").feature("lngr1").set("expr", "pH");
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u7535\u6781\u53cd\u5e94\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg10").create("ptgr1", "PointGraph");
    model.result("pg10").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg10").feature("ptgr1").set("linewidth", "preference");
    model.result("pg10").feature("ptgr1").selection().set(1);
    model.result("pg10").feature("ptgr1").set("expr", "tcd.iloc_er1");
    model.result("pg10").feature("ptgr1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg10").feature("ptgr1").set("legend", true);
    model.result("pg10").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg10").feature("ptgr1").setIndex("legends", "\u6790\u6c27", 0);
    model.result("pg10").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg10").run();
    model.result("pg10").feature("ptgr2").set("expr", "tcd.iloc_er2");
    model.result("pg10").feature("ptgr2").set("titletype", "none");
    model.result("pg10").feature("ptgr2").set("linestyle", "dashdot");
    model.result("pg10").feature("ptgr2").setIndex("legends", "\u6790\u6c2f", 0);
    model.result("pg10").run();
    model.result("pg10").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg10").run();
    model.result("pg10").feature("ptgr3").set("expr", "tcd.itot");
    model.result("pg10").feature("ptgr3").set("descr", "\u754c\u9762\u603b\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg10").feature("ptgr3").set("linestyle", "dashed");
    model.result("pg10").feature("ptgr3").setIndex("legends", "\u603b\u7535\u6781\u7535\u6d41\u5bc6\u5ea6", 0);
    model.result("pg10").run();
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result("pg3").run();

    model.title("\u80bf\u7624\u7684\u7535\u5316\u5b66\u7597\u6cd5");

    model
         .description("\u672c\u4f8b\u5904\u7406\u809d\u810f\u80bf\u7624\u7ec4\u7ec7\u5728\u7535\u89e3\u533b\u7597\u8fc7\u7a0b\u4e2d\u7684\u4f20\u9012\u548c\u53cd\u5e94\u3002\u6a21\u578b\u4e2d\u6d89\u53ca\u7684\u7269\u8d28\u5305\u62ec\u8d28\u5b50\u3001\u6c2f\u79bb\u5b50\u4ee5\u53ca\u94a0\u79bb\u5b50\u3002\u5728\u9633\u6781\u8868\u9762\u4e0a\u7684\u53cd\u5e94\u4e3b\u8981\u662f\u6c2f\u79bb\u5b50\u548c\u6c27\u79bb\u5b50\u7684\u7535\u5316\u5b66\u53cd\u5e94\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("tumor.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
