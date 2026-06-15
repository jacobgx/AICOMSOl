/*
 * stefan_tube.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:21 by COMSOL 6.3.0.290. */
public class stefan_tube {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Mixing_and_Separation");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcs", "ConcentratedSpecies", "geom1");
    model.component("comp1").physics("tcs").field("massfraction").component(new String[]{"w1", "w2", "w3"});

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tcs", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("p0", "1.013e5[Pa]", "\u538b\u529b");
    model.param().set("T0", "328.5[K]", "\u6e29\u5ea6");
    model.param().set("D12", "8.48e-6[m^2/s]", "MS \u6269\u6563\u7cfb\u6570\uff0c\u4e19\u916e");
    model.param().set("D13", "13.72e-6[m^2/s]", "MS \u6269\u6563\u7cfb\u6570\uff0c\u7532\u9187");
    model.param().set("D23", "19.91e-6[m^2/s]", "MS \u6269\u6563\u7cfb\u6570\uff0c\u7a7a\u6c14");
    model.param().set("M_air", "28.8[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u7a7a\u6c14");
    model.param().set("M_ace", "58[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u4e19\u916e");
    model.param().set("M_met", "32[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u4e59\u9187");
    model.param().set("x_ace0", "0.319", "\u6db2\u4f53\u754c\u9762\u4e0a\u4e19\u916e\u7684\u6469\u5c14\u5206\u6570");
    model.param().set("x_met0", "0.528", "\u6db2\u4f53\u754c\u9762\u4e0a\u7532\u9187\u7684\u6469\u5c14\u5206\u6570");
    model.param()
         .set("x_air0", "1-x_ace0-x_met0", "\u6db2\u4f53\u754c\u9762\u4e0a\u7a7a\u6c14\u7684\u6469\u5c14\u5206\u6570");
    model.param()
         .set("w_air0", "x_air0*M_air/(x_air0*M_air+x_ace0*M_ace+x_met0*M_met)", "\u6db2\u4f53\u754c\u9762\u4e0a\u7a7a\u6c14\u7684\u8d28\u91cf\u5206\u6570");
    model.param()
         .set("w_ace0", "x_ace0*M_ace/(x_air0*M_air+x_ace0*M_ace+x_met0*M_met)", "\u6db2\u4f53\u754c\u9762\u4e0a\u4e19\u916e\u7684\u8d28\u91cf\u5206\u6570");
    model.param()
         .set("w_met0", "1-w_air0-w_ace0", "\u6db2\u4f53\u754c\u9762\u4e0a\u7532\u9187\u7684\u8d28\u91cf\u5206\u6570");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", 0.238, 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("tcs").prop("TransportMechanism").set("DiffusionModel", "MaxwellStefan");
    model.component("comp1").physics("tcs").prop("SpeciesProperties").set("FromMassConstraint", 3);
    model.component("comp1").physics("tcs").prop("ShapeProperty").set("order_massfraction", 2);
    model.component("comp1").physics("tcs").feature("sp1").setIndex("M_w1", "M_ace", 0);
    model.component("comp1").physics("tcs").feature("sp1").setIndex("M_w2", "M_met", 0);
    model.component("comp1").physics("tcs").feature("sp1").setIndex("M_w3", "M_air", 0);
    model.component("comp1").physics("tcs").feature("cdm1").set("minput_temperature_src", "userdef");
    model.component("comp1").physics("tcs").feature("cdm1").set("minput_temperature", "T0");
    model.component("comp1").physics("tcs").feature("cdm1").set("minput_pressure_src", "userdef");
    model.component("comp1").physics("tcs").feature("cdm1").set("minput_pressure", "p0");
    model.component("comp1").physics("tcs").feature("cdm1")
         .set("u", new String[]{"-tcs.dflux_w3x/(w3*tcs.rho)", "0", "0"});
    model.component("comp1").physics("tcs").feature("cdm1").setIndex("DF", "D12", 0, 0);
    model.component("comp1").physics("tcs").feature("cdm1").setIndex("DF", "D13", 1, 0);
    model.component("comp1").physics("tcs").feature("cdm1").setIndex("DF", "D23", 2, 0);
    model.component("comp1").physics("tcs").feature("init1").setIndex("w0", "w_ace0", 0);
    model.component("comp1").physics("tcs").feature("init1").setIndex("w0", "w_met0", 1);
    model.component("comp1").physics("tcs").create("mf1", "MassFraction", 0);
    model.component("comp1").physics("tcs").feature("mf1").selection().set(1);
    model.component("comp1").physics("tcs").feature("mf1").setIndex("species", true, 0);
    model.component("comp1").physics("tcs").feature("mf1").setIndex("wbnd", "w_ace0", 0);
    model.component("comp1").physics("tcs").feature("mf1").setIndex("species", true, 1);
    model.component("comp1").physics("tcs").feature("mf1").setIndex("wbnd", "w_met0", 1);
    model.component("comp1").physics("tcs").create("mf2", "MassFraction", 0);
    model.component("comp1").physics("tcs").feature("mf2").selection().set(2);
    model.component("comp1").physics("tcs").feature("mf2").setIndex("species", true, 0);
    model.component("comp1").physics("tcs").feature("mf2").setIndex("species", true, 1);

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28");
    model.result("pg1").label("\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28 (tcs)");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", new String[]{"tcs.c_w1"});
    model.result("pg1").feature("lngr1").label("\u7269\u8d28 w1");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("autosolution", false);
    model.result("pg1").feature("lngr1").set("autoexpr", false);
    model.result("pg1").feature("lngr1").set("autodescr", false);
    model.result("pg1").feature("lngr1").set("legendprefix", "w1 ");
    model.result("pg1").feature("lngr1").set("descractive", true);
    model.result("pg1").feature("lngr1").set("descr", "\u6d53\u5ea6");
    model.result("pg1").create("lngr2", "LineGraph");
    model.result("pg1").feature("lngr2").set("xdata", "expr");
    model.result("pg1").feature("lngr2").set("xdataexpr", "x");
    model.result("pg1").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr2").selection().set(1);
    model.result("pg1").feature("lngr2").set("expr", new String[]{"tcs.c_w2"});
    model.result("pg1").feature("lngr2").label("\u7269\u8d28 w2");
    model.result("pg1").feature("lngr2").set("legend", true);
    model.result("pg1").feature("lngr2").set("autosolution", false);
    model.result("pg1").feature("lngr2").set("autoexpr", false);
    model.result("pg1").feature("lngr2").set("autodescr", false);
    model.result("pg1").feature("lngr2").set("legendprefix", "w2 ");
    model.result("pg1").feature("lngr2").set("descractive", true);
    model.result("pg1").feature("lngr2").set("descr", "\u6d53\u5ea6");
    model.result("pg1").create("lngr3", "LineGraph");
    model.result("pg1").feature("lngr3").set("xdata", "expr");
    model.result("pg1").feature("lngr3").set("xdataexpr", "x");
    model.result("pg1").feature("lngr3").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr3").selection().set(1);
    model.result("pg1").feature("lngr3").set("expr", new String[]{"tcs.c_w3"});
    model.result("pg1").feature("lngr3").label("\u7269\u8d28 w3");
    model.result("pg1").feature("lngr3").set("legend", true);
    model.result("pg1").feature("lngr3").set("autosolution", false);
    model.result("pg1").feature("lngr3").set("autoexpr", false);
    model.result("pg1").feature("lngr3").set("autodescr", false);
    model.result("pg1").feature("lngr3").set("legendprefix", "w3 ");
    model.result("pg1").feature("lngr3").set("descractive", true);
    model.result("pg1").feature("lngr3").set("descr", "\u6d53\u5ea6");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u6d53\u5ea6, w1 (tcs)");
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").set("prefixintitle", "");
    model.result("pg2").set("expressionintitle", false);
    model.result("pg2").set("typeintitle", false);
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature("lngr1").set("expr", new String[]{"tcs.c_w1"});
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u6d53\u5ea6, w2 (tcs)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"tcs.c_w2"});
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u6d53\u5ea6, w3 (tcs)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1);
    model.result("pg4").feature("lngr1").set("expr", new String[]{"tcs.c_w3"});
    model.result("pg1").run();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").label("\u5b9e\u9a8c\u6469\u5c14\u5206\u6570");
    model.result().table("tbl1").importData("stefan_tube_exp.csv");
    model.result("pg1").run();
    model.result("pg1").set("legendlayout", "outside");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u6469\u5c14\u5206\u6570\uff0c\u6240\u6709\u7269\u8d28");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u4f4d\u7f6e (m)");
    model.result("pg5").set("legendlayout", "outside");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").selection().all();
    model.result("pg5").feature("lngr1").set("expr", "tcs.x_w1");
    model.result("pg5").feature("lngr1").set("descr", "\u6469\u5c14\u5206\u6570");
    model.result("pg5").feature("lngr1").set("titletype", "none");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").set("linewidth", 2);
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "manual");
    model.result("pg5").feature("lngr1").setIndex("legends", "\u4e19\u916e", 0);
    model.result("pg5").feature().duplicate("lngr2", "lngr1");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").set("expr", "tcs.x_w2");
    model.result("pg5").feature("lngr2").set("descr", "\u6469\u5c14\u5206\u6570");
    model.result("pg5").feature("lngr2").setIndex("legends", "\u7532\u9187", 0);
    model.result("pg5").feature().duplicate("lngr3", "lngr2");
    model.result("pg5").run();
    model.result("pg5").feature("lngr3").set("expr", "tcs.x_w3");
    model.result("pg5").feature("lngr3").set("descr", "\u6469\u5c14\u5206\u6570");
    model.result("pg5").feature("lngr3").setIndex("legends", "\u7a7a\u6c14", 0);
    model.result("pg5").run();
    model.result("pg5").create("tblp1", "Table");
    model.result("pg5").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg5").feature("tblp1").set("linewidth", "preference");
    model.result("pg5").feature("tblp1").set("xaxisdata", 1);
    model.result("pg5").feature("tblp1").set("linemarker", "cycle");
    model.result("pg5").feature("tblp1").set("linestyle", "none");
    model.result("pg5").feature("tblp1").set("linecolor", "cyclereset");
    model.result("pg5").feature("tblp1").set("legend", true);
    model.result("pg5").feature("tblp1").set("legendsuffix", " expr.");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").create("comp1", "Comparison");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").feature("comp1").set("column", 2);
    model.result("pg5").feature("lngr1").feature("comp1").set("metric", "rms");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").create("comp1", "Comparison");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").feature("comp1").set("column", 3);
    model.result("pg5").feature("lngr2").feature("comp1").set("metric", "rms");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("lngr3").create("comp1", "Comparison");
    model.result("pg5").run();
    model.result("pg5").feature("lngr3").feature("comp1").set("column", 4);
    model.result("pg5").feature("lngr3").feature("comp1").set("metric", "rms");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u901f\u5ea6\u573a");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u4f4d\u7f6e (m)");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").selection().set(1);
    model.result("pg6").feature("lngr1").set("expr", "tcs.u");
    model.result("pg6").feature("lngr1").set("descr", "\u901f\u5ea6\u573a\uff0cx \u5206\u91cf");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").set("linewidth", 2);
    model.result("pg6").run();
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result().remove("pg3");
    model.result().remove("pg4");
    model.result("pg5").run();

    model.title("Stefan \u7ba1");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u7a33\u6001\u591a\u7ec4\u5206\u6c14\u4f53\u6269\u6563\u7684\u4e00\u7ef4\u6a21\u578b\u793a\u4f8b\uff0c\u4f7f\u7528\u201c\u6d53\u7269\u8d28\u4f20\u9012\u201d\u63a5\u53e3\u548c Maxwell-Stefan \u6269\u6563\u673a\u5236\u5bf9 Stefan \u7ba1\u4e2d\u4e09\u79cd\u6c14\u4f53\u7684\u6269\u6563\u8fdb\u884c\u5efa\u6a21\uff0c\u5e76\u6839\u636e\u5b9e\u9a8c\u6570\u636e\u8ba1\u7b97\u548c\u9a8c\u8bc1\u8fd9\u4e9b\u6c14\u4f53\u7684\u7a33\u6001\u6469\u5c14\u5206\u6570\u5206\u5e03\u3002");

    model.label("stefan_tube.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
