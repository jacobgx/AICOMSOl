/*
 * effective_diffusivity.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:33 by COMSOL 6.3.0.290. */
public class effective_diffusivity {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Diffusion");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/tds", true);

    model.component("comp1").geom("geom1").insertFile("effective_diffusivity_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("sel3");

    model.param().set("D2", "1e-5[m^2/s]");
    model.param().descr("D2", "\u6269\u6563\u7cfb\u6570");
    model.param().set("c_max", "3[mol/m^3]");
    model.param().descr("c_max", "\u521d\u59cb\u6d53\u5ea6\u5cf0\u503c");
    model.param().set("k_f", "5[m/s]");
    model.param().descr("k_f", "\u8d28\u91cf\u4f20\u9012\u7cfb\u6570");
    model.param().set("a", "1000");
    model.param().descr("a", "\u65e0\u91cf\u7eb2\u5316\u5e38\u6570");

    model.variable().create("var1");
    model.variable("var1").set("c0", "c_max*exp(a*(-(x/0.4[mm])^2))");
    model.variable("var1").descr("c0", "\u521d\u59cb\u6d53\u5ea6");

    model.component("comp1").physics("tds").prop("TransportMechanism").set("Convection", false);
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_c", new String[]{"D2", "0", "0", "0", "D2", "0", "0", "0", "D2"});
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "c0", 0);
    model.component("comp1").physics("tds").create("conc1", "Concentration", 1);
    model.component("comp1").physics("tds").feature("conc1").selection().named("geom1_sel2");
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "c_max", 0);
    model.component("comp1").physics("tds").create("fl1", "FluxBoundary", 1);
    model.component("comp1").physics("tds").feature("fl1").selection().named("geom1_sel3");
    model.component("comp1").physics("tds").feature("fl1").set("FluxType", "ExternalConvection");
    model.component("comp1").physics("tds").feature("fl1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("fl1").setIndex("kc", "k_f", 0);

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().named("geom1_sel3");

    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").set("flux_avg", "aveop1(k_f*c)");
    model.component("comp1").variable("var2").descr("flux_avg", "\u5e73\u5747\u901a\u91cf");

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "ms");
    model.study("std1").feature("time").set("tlist", "range(0,2,100)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 51, 0);
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
    model.result("pg1").feature("arws1").set("expr", new String[]{"tds.tflux_cx", "tds.tflux_cy"});
    model.result("pg1").feature("arws1").set("xnumber", 10);
    model.result("pg1").feature("arws1").set("ynumber", 10);
    model.result("pg1").feature("arws1").set("color", "black");
    model.result("pg1").feature("arws1").create("sel1", "Selection");
    model.result("pg1").feature("arws1").feature("sel1").selection().set(1);
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").run();
    model.result("pg1").feature().remove("arws1");
    model.result("pg1").run();
    model.result("pg1").feature("str1").selection().set(1);
    model.result("pg1").feature("str1").set("selnumber", 40);
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 26, 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().named("geom1_sel1");
    model.result("pg2").feature("ptgr1").set("expr", "flux_avg");
    model.result("pg2").feature("ptgr1").set("descr", "\u5e73\u5747\u901a\u91cf");
    model.result("pg2").run();
    model.result("pg2").label("\u6469\u5c14\u901a\u91cf");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u5e73\u5747\u901a\u91cf (mol/(m*s))");
    model.result("pg2").run();
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").selection().all();
    model.result().numerical("int1").setIndex("expr", "1/(0.8[mm])^2", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8868\u9762\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 1);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").physics().create("tds2", "DilutedSpecies", "geom2");

    model.study("std1").feature("time").setSolveFor("/physics/tds2", false);

    model.component("comp2").geom("geom2").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/tds", false);
    model.study("std2").feature("time").setSolveFor("/physics/tds2", true);

    model.component("comp2").geom("geom2").create("i1", "Interval");
    model.component("comp2").geom("geom2").feature("i1").setIndex("coord", "8e-4", 1);
    model.component("comp2").geom("geom2").run("i1");

    model.param().set("epsilon", "0.383");
    model.param().descr("epsilon", "\u5b54\u9699\u7387");
    model.param().set("D1", "2.15e-6[m^2/s]");
    model.param().descr("D1", "\u6269\u6563\u7cfb\u6570\uff0c\u4e00\u7ef4");

    model.component("comp2").geom("geom2").run();

    model.component("comp2").physics("tds2").prop("TransportMechanism").set("Convection", false);
    model.component("comp2").physics("tds2").feature("cdm1")
         .set("D_c2", new String[]{"D1/epsilon", "0", "0", "0", "D1/epsilon", "0", "0", "0", "D1/epsilon"});
    model.component("comp2").physics("tds2").feature("init1").setIndex("initc", "c0", 0);
    model.component("comp2").physics("tds2").create("conc1", "Concentration", 0);
    model.component("comp2").physics("tds2").feature("conc1").selection().set(1);
    model.component("comp2").physics("tds2").feature("conc1").setIndex("species", true, 0);
    model.component("comp2").physics("tds2").feature("conc1").setIndex("c0", "c_max", 0);
    model.component("comp2").physics("tds2").create("fl1", "FluxBoundary", 0);
    model.component("comp2").physics("tds2").feature("fl1").selection().set(2);
    model.component("comp2").physics("tds2").feature("fl1").set("FluxType", "ExternalConvection");
    model.component("comp2").physics("tds2").feature("fl1").setIndex("species", true, 0);
    model.component("comp2").physics("tds2").feature("fl1").setIndex("kc", "k_f/epsilon", 0);

    model.component("comp2").variable().create("var3");
    model.component("comp2").variable("var3").set("flux_hom", "k_f*c2");
    model.component("comp2").variable("var3").descr("flux_hom", "\u901a\u91cf\uff0c\u4e00\u7ef4\u6a21\u578b");

    model.component("comp2").mesh("mesh2").autoMeshSize(2);
    model.component("comp2").mesh("mesh2").run();

    model.study("std2").feature("time").set("tunit", "ms");
    model.study("std2").feature("time").set("tlist", "range(0,2,100)");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").label("\u6d53\u5ea6 (tds2)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").selection().geom("geom2", 1);
    model.result("pg3").feature("lngr1").selection().set(1);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"c2"});
    model.result("pg3").run();
    model.result("pg2").run();
    model.result("pg2").create("ptgr2", "PointGraph");
    model.result("pg2").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr2").set("linewidth", "preference");
    model.result("pg2").feature("ptgr2").set("data", "dset3");
    model.result("pg2").feature("ptgr2").selection().set(2);
    model.result("pg2").feature("ptgr2").set("expr", "flux_hom");
    model.result("pg2").feature("ptgr2").set("descr", "\u901a\u91cf\uff0c\u4e00\u7ef4\u6a21\u578b");
    model.result("pg2").feature("ptgr2").set("linestyle", "dashed");
    model.result("pg2").run();
    model.result("pg1").run();

    model.title("\u591a\u5b54\u6750\u6599\u7684\u6709\u6548\u6269\u6563\u7cfb\u6570");

    model
         .description("\u672c\u4f8b\u4ecb\u7ecd\u591a\u5b54\u4ecb\u8d28\u7684\u6709\u6548\u6269\u6563\u7cfb\u6570\u6982\u5ff5\uff0c\u6bd4\u8f83\u4e86\u901a\u8fc7\u4eba\u9020\u7684\u591a\u5b54\u4ecb\u8d28\uff08\u5728\u8be6\u7ec6\u6a21\u578b\u4e2d\u63cf\u8ff0\uff09\u8fdb\u884c\u7684\u4f20\u9012\uff0c\u4ee5\u53ca\u4f7f\u7528\u6709\u6548\u4f20\u9012\u5c5e\u6027\u7684\u7b80\u5316\u5747\u8d28\u591a\u5b54\u4ecb\u8d28\u65b9\u6cd5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("effective_diffusivity.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
