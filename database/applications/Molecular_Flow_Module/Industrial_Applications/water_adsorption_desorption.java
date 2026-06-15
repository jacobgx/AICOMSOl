/*
 * water_adsorption_desorption.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:18 by COMSOL 6.3.0.290. */
public class water_adsorption_desorption {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Molecular_Flow_Module\\Industrial_Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("fmf", "FreeMolecularFlow", "geom1");
    model.component("comp1").physics("fmf").field("particleflux").field("H2O");
    model.component("comp1").physics("fmf").field("particleflux").component(new String[]{"H2O"});

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/fmf", true);

    model.param().set("nsites", "1e-4[mol/m^2]");
    model.param().descr("nsites", "\u5438\u9644\u4f4d\u5bc6\u5ea6");
    model.param().set("tau", "200[s]");
    model.param().descr("tau", "\u89e3\u5438\u7684\u65f6\u95f4\u5e38\u6570");
    model.param().set("sc", "0.1");
    model.param().descr("sc", "\u9ecf\u9644\u7cfb\u6570");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "16[in]");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("rot", 270);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"14[in]", "22[in]"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-46[in]"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"7.5[in]", "70[in]"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "-50[in]"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "7.5[in]", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "-20[in]", 1);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("c1", "r1", "r2");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"1.5[in]", "50[in]"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"0", "-50[in]"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("co1", "Compose");
    model.component("comp1").geom("geom1").feature("co1").selection("input").set("r3", "uni1");
    model.component("comp1").geom("geom1").feature("co1").set("formula", "uni1-r3");
    model.component("comp1").geom("geom1").run("co1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("fmf").feature("fmfp1").setIndex("Mn_H2O", "0.018[kg/mol]", 0);
    model.component("comp1").physics("fmf").feature("wall1").set("BCType", "AdsorptionDesorption");
    model.component("comp1").physics("fmf").feature("wall1").setIndex("S", "sc*(1-fmf.n_ads_H2O/nsites)", 0);
    model.component("comp1").physics("fmf").feature("wall1").setIndex("D", "fmf.n_ads_H2O/tau", 0);
    model.component("comp1").physics("fmf").feature("wall1").setIndex("n_ads_0", 0, 0);
    model.component("comp1").physics("fmf").feature().duplicate("wall2", "wall1");
    model.component("comp1").physics("fmf").feature("wall2").selection().set(2, 5, 6, 7, 8, 9, 10, 13);
    model.component("comp1").physics("fmf").feature("wall2").setIndex("n_ads_0", "nsites", 0);
    model.component("comp1").physics("fmf").create("pmp1", "VacuumPump", 1);
    model.component("comp1").physics("fmf").feature("pmp1").selection().set(4);
    model.component("comp1").physics("fmf").feature("pmp1").set("SpecifyPump", "PumpSpeed");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().all();
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time")
         .set("tlist", "range(0,100,1000) range(2000,1000,10000) 1e4*10^{range(0,0.1,1)}");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "0.0001");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "manual");
    model.sol("sol1").feature("t1").set("atolglobal", "0.00001");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", 270);
    model.result().dataset("rev1").set("revangle", 270);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u5165\u5c04\u5206\u5b50\u901a\u91cf (fmf)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").feature().create("line1", "Line");
    model.result("pg1").feature("line1").label("\u7ebf");
    model.result("pg1").feature("line1").set("showsolutionparams", "on");
    model.result("pg1").feature("line1").set("expr", "fmf.Gtot");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("resolution", "norefine");
    model.result("pg1").feature("line1").set("smooth", "internal");
    model.result("pg1").feature("line1").set("showsolutionparams", "on");
    model.result("pg1").feature("line1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u603b\u6570\u5bc6\u5ea6 (fmf)");
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").feature().create("line1", "Line");
    model.result("pg2").feature("line1").label("\u7ebf");
    model.result("pg2").feature("line1").set("showsolutionparams", "on");
    model.result("pg2").feature("line1").set("expr", "fmf.ntot");
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("resolution", "norefine");
    model.result("pg2").feature("line1").set("smooth", "internal");
    model.result("pg2").feature("line1").set("showsolutionparams", "on");
    model.result("pg2").feature("line1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u603b\u538b (fmf)");
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").feature().create("line1", "Line");
    model.result("pg3").feature("line1").label("\u7ebf");
    model.result("pg3").feature("line1").set("showsolutionparams", "on");
    model.result("pg3").feature("line1").set("expr", "fmf.ptot");
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("resolution", "norefine");
    model.result("pg3").feature("line1").set("smooth", "internal");
    model.result("pg3").feature("line1").set("showsolutionparams", "on");
    model.result("pg3").feature("line1").set("data", "parent");
    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u5438\u9644\u7684\u5206\u5b50");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "fmf.N_ads_H2O");
    model.result("pg4").feature("surf1")
         .set("descr", "\u5355\u4f4d\u9762\u79ef\u7684\u5438\u9644\u5206\u5b50\u6570");
    model.result("pg4").feature("surf1").set("colortable", "Plasma");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 13, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 8, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u538b\u529b vs. \u65f6\u95f4");
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("xmin", 0.02);
    model.result("pg5").set("xmax", 40);
    model.result("pg5").set("ymin", "4e-9");
    model.result("pg5").set("ymax", "1.8e-6");
    model.result("pg5").set("xlog", true);
    model.result("pg5").set("ylog", true);
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").selection().set(13, 15);
    model.result("pg5").feature("ptgr1").set("expr", "fmf.ptot");
    model.result("pg5").feature("ptgr1").set("unit", "torr");
    model.result("pg5").feature("ptgr1").set("xdata", "expr");
    model.result("pg5").feature("ptgr1").set("xdataexpr", "t");
    model.result("pg5").feature("ptgr1").set("xdataunit", "h");
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr1").setIndex("legends", "\u8d1f\u8f7d\u9501\u5b9a", 0);
    model.result("pg5").feature("ptgr1").setIndex("legends", "\u8154\u5ba4", 1);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u5438\u9644\u7684\u5206\u5b50 vs. \u65f6\u95f4");
    model.result("pg6").set("ymin", "5e17");
    model.result("pg6").set("ymax", 1.1E20);
    model.result("pg6").run();
    model.result("pg6").feature("ptgr1").set("expr", "fmf.N_ads_H2O");
    model.result("pg6").feature("ptgr1")
         .set("descr", "\u5355\u4f4d\u9762\u79ef\u7684\u5438\u9644\u5206\u5b50\u6570");
    model.result("pg6").run();

    model.title("\u8d1f\u8f7d\u9501\u5b9a\u771f\u7a7a\u7cfb\u7edf\u4e2d\u6c34\u7684\u5438\u9644\u548c\u89e3\u5438");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u6c34\u5728\u4f4e\u538b\u771f\u7a7a\u7cfb\u7edf\u4e2d\u7684\u77ac\u6001\u5438\u9644\u548c\u89e3\u5438\u3002\u5f53\u8d1f\u8f7d\u9501\u4e0a\u7684\u95f8\u9600\u6253\u5f00\u65f6\uff0c\u6c34\u88ab\u5f15\u5165\u7cfb\u7edf\u4e2d\uff0c\u672c\u4f8b\u5bf9\u6c34\u968f\u540e\u4ea7\u751f\u7684\u8fc1\u79fb\u548c\u6cf5\u9001\u8fdb\u884c\u5efa\u6a21\u3002");

    model.label("water_adsorption_desorption.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
