/*
 * chicken_patties.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:24 by COMSOL 6.3.0.290. */
public class chicken_patties {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Phase_Change");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/tds", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);

    model.param().set("T_air", "135[degC]");
    model.param().descr("T_air", "\u70e4\u7bb1\u7a7a\u6c14\u6e29\u5ea6");
    model.param().set("T0", "22[degC]");
    model.param().descr("T0", "\u521d\u59cb\u9e21\u8089\u997c\u6e29\u5ea6");
    model.param().set("rho_p", "1100[kg/m^3]");
    model.param().descr("rho_p", "\u9e21\u8089\u997c\u5bc6\u5ea6");
    model.param().set("h_T", "25[W/(m^2*K)]");
    model.param().descr("h_T", "\u4f20\u70ed\u7cfb\u6570");
    model.param().set("M_H2O", "18[g/mol]");
    model.param().descr("M_H2O", "\u6c34\u5206\u5b50\u91cf");
    model.param().set("c0", "0.78*rho_p/M_H2O");
    model.param().descr("c0", "\u521d\u59cb\u6c34\u5206\u6d53\u5ea6");
    model.param().set("c_b", "0.02*rho_p/M_H2O");
    model.param().descr("c_b", "\u7a7a\u6c14\u6c34\u5206\u6d53\u5ea6");
    model.param().set("C_m", "0.003");
    model.param().descr("C_m", "\u6bd4\u5bb9\u6c34\u5ea6");
    model.param().set("k_m", "1.29e-9[kg/(m*s)]");
    model.param().descr("k_m", "\u5bfc\u6e7f\u6027");
    model.param().set("h_m", "1.67e-6[kg/(m^2*s)]");
    model.param()
         .descr("h_m", "\u8d28\u91cf\u4f20\u9012\u7cfb\u6570\uff0c\u4ee5\u8d28\u91cf\u5355\u4f4d\u8868\u793a");
    model.param().set("D", "k_m/(rho_p*C_m)");
    model.param().descr("D", "\u6269\u6563\u7cfb\u6570");
    model.param().set("k_c", "h_m/(rho_p*C_m)");
    model.param().descr("k_c", "\u8d28\u91cf\u4f20\u9012\u7cfb\u6570");
    model.param().set("lda", "2.3e6[J/kg]*M_H2O");
    model.param().descr("lda", "\u6469\u5c14\u6c7d\u5316\u6f5c\u70ed");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{31, 5});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("k_T", "(0.194+0.436*c*M_H2O/rho_p)[W/(m*K)]");
    model.component("comp1").variable("var1").descr("k_T", "\u5bfc\u70ed\u7cfb\u6570");
    model.component("comp1").variable("var1").set("dT", "(T-0[degC])[1/K]");
    model.component("comp1").variable("var1").descr("dT", "\u6e29\u5dee");
    model.component("comp1").variable("var1").set("C_p", "(3017.2+2.05*dT+0.24*dT^2+0.002*dT^3)[J/(kg*K)]");
    model.component("comp1").variable("var1").descr("C_p", "\u6bd4\u70ed");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u9e21\u8089");
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"k_T"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho_p"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"C_p"});

    model.component("comp1").physics("tds").prop("TransportMechanism").set("Convection", false);
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_c", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "c0", 0);
    model.component("comp1").physics("tds").create("fl1", "FluxBoundary", 1);
    model.component("comp1").physics("tds").feature("fl1").selection().set(3, 4);
    model.component("comp1").physics("tds").feature("fl1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("fl1").set("FluxType", "ExternalConvection");
    model.component("comp1").physics("tds").feature("fl1").setIndex("kc", "k_c", 0);
    model.component("comp1").physics("tds").feature("fl1").setIndex("cb", "c_b", 0);
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(3, 4);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "h_T");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "T_air");
    model.component("comp1").physics("ht").create("bhs1", "BoundaryHeatSource", 1);
    model.component("comp1").physics("ht").feature("bhs1").selection().set(3, 4);
    model.component("comp1").physics("ht").feature("bhs1").set("Qb_input", "lda*k_c*(c_b-c)");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(3, 4);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 0.1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,10,900)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 91, 0);
    model.result("pg1").label("\u6d53\u5ea6 (tds)");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").set("prefixintitle", "");
    model.result("pg1").set("expressionintitle", false);
    model.result("pg1").set("typeintitle", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").set("typeintitle", true);
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"tds.tflux_cr", "tds.tflux_cz"});
    model.result("pg1").feature("arws1").set("xnumber", 10);
    model.result("pg1").feature("arws1").set("ynumber", 10);
    model.result("pg1").feature("arws1").set("color", "black");
    model.result("pg1").feature("arws1").create("sel1", "Selection");
    model.result("pg1").feature("arws1").feature("sel1").selection().set(1);
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", false);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "rev1");
    model.result("pg2").setIndex("looplevel", 91, 0);
    model.result("pg2").label("\u6d53\u5ea6, 3D (tds)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").set("typeintitle", false);
    model.result("pg2").set("prefixintitle", "");
    model.result("pg2").set("expressionintitle", false);
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "T");
    model.result("pg3").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 78, 0);
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 78, 0);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u6e29\u5ea6\u66f2\u7ebf vs. \u65f6\u95f4");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").selection().set(1);
    model.result("pg4").feature("ptgr1").set("expr", "T");
    model.result("pg4").feature("ptgr1").set("descr", "\u6e29\u5ea6");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().numerical().create("av1", "AvVolume");
    model.result().numerical("av1").setIndex("looplevelinput", "manual", 0);
    model.result().numerical("av1").setIndex("looplevel", new int[]{38}, 0);
    model.result().numerical("av1").setIndex("expr", "c/c0", 0);
    model.result().numerical("av1").setIndex("descr", "\u70f9\u996a\u6210\u719f\u5ea6", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u4f53\u79ef\u5e73\u5747 1");
    model.result().numerical("av1").set("table", "tbl1");
    model.result().numerical("av1").setResult();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "T_air", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "K", 0);
    model.study("std1").feature("param").setIndex("pname", "T_air", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "K", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(135,42,219)", 0);
    model.study("std1").feature("param").setIndex("punit", "degC", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 91, 0);
    model.result("pg5").setIndex("looplevel", 3, 1);
    model.result("pg5").label("\u6d53\u5ea6 (tds) 1");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").set("typeintitle", true);
    model.result("pg5").create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").set("expr", new String[]{"tds.tflux_cr", "tds.tflux_cz"});
    model.result("pg5").feature("arws1").set("xnumber", 10);
    model.result("pg5").feature("arws1").set("ynumber", 10);
    model.result("pg5").feature("arws1").set("color", "black");
    model.result("pg5").feature("arws1").create("sel1", "Selection");
    model.result("pg5").feature("arws1").feature("sel1").selection().set(1);
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("data", "dset2");
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("hasspacevars", false);
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "rev2");
    model.result("pg6").setIndex("looplevel", 91, 0);
    model.result("pg6").setIndex("looplevel", 3, 1);
    model.result("pg6").label("\u6d53\u5ea6, 3D (tds) 1");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").set("prefixintitle", "");
    model.result("pg6").set("expressionintitle", false);
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevel", 91, 0);
    model.result("pg7").setIndex("looplevel", 3, 1);
    model.result("pg7").set("dataisaxisym", "off");
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("solutionparams", "parent");
    model.result("pg7").feature("surf1").set("expr", "T");
    model.result("pg7").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result("pg5").run();
    model.result("pg4").run();
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("legendpos", "lowerright");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr1").set("xdatasolnumtype", "inner");
    model.result("pg4").feature("ptgr1").set("legend", true);
    model.result("pg4").feature("ptgr1").set("legendmethod", "evaluated");
    model.result("pg4").feature("ptgr1")
         .set("legendpattern", "eval(T_air,degC)\u00b0C \u65f6\u7684\u7a7a\u6c14\u6e29\u5ea6");
    model.result("pg4").run();
    model.result("pg3").run();
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 38, 0);
    model.result("pg3").run();
    model.result().numerical().create("av2", "AvVolume");
    model.result().numerical("av2").set("data", "rev2");
    model.result().numerical("av2").setIndex("looplevelinput", "manual", 1);
    model.result().numerical("av2").setIndex("looplevel", new int[]{3}, 1);
    model.result().numerical("av2").setIndex("looplevelinput", "manual", 0);
    model.result().numerical("av2").setIndex("looplevel", new int[]{38}, 0);
    model.result().numerical("av2").set("tablecols", "level2");
    model.result().numerical("av2").setIndex("expr", "c/c0", 0);
    model.result().numerical("av2").setIndex("descr", "\u70f9\u996a\u6210\u719f\u5ea6", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u4f53\u79ef\u5e73\u5747 2");
    model.result().numerical("av2").set("table", "tbl2");
    model.result().numerical("av2").setResult();
    model.result("pg6").run();

    model.title("\u9e21\u8089\u997c\u7684\u5bf9\u6d41\u70f9\u996a");

    model
         .description("\u5bf9\u70f9\u996a\u9e21\u8089\u997c\u8fc7\u7a0b\u4e2d\uff0c\u4f20\u5bfc\u4f20\u70ed\u4e0e\u6c34\u5206\u8f93\u9001\u76f8\u8026\u5408\u7684\u73b0\u8c61\u8fdb\u884c\u77ac\u6001\u5206\u6790\u3002\u8fd9\u662f\u4e00\u4e2a\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u6a21\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("chicken_patties.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
