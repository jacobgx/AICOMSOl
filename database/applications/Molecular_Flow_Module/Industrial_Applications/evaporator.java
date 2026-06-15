/*
 * evaporator.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:18 by COMSOL 6.3.0.290. */
public class evaporator {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Molecular_Flow_Module\\Industrial_Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("fmf", "FreeMolecularFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/fmf", true);

    model.component("comp1").geom("geom1").insertFile("evaporator_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("sel5");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.param().set("Tamb", "293.15[K]");
    model.param().descr("Tamb", "\u73af\u5883\u6e29\u5ea6");
    model.param().set("Tevap", "2000[K]");
    model.param().descr("Tevap", "\u84b8\u53d1\u6e29\u5ea6");
    model.param().set("pvap", "50[Pa]");
    model.param().descr("pvap", "\u91d1\u7684\u84b8\u6c7d\u538b");
    model.param().set("Mn0", "197[g/mol]");
    model.param().descr("Mn0", "\u91d1\u7684\u5206\u5b50\u91cf");
    model.param().set("rho0", "19.3[g/cm^3]");
    model.param().descr("rho0", "\u91d1\u7684\u5bc6\u5ea6");

    model.component("comp1").selection().create("ball1", "Ball");
    model.component("comp1").selection("ball1").label("\u94a8\u821f");
    model.component("comp1").selection("ball1").set("entitydim", 2);
    model.component("comp1").selection("ball1").set("r", 10);
    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u6240\u6709\u8fb9\u754c");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").all();
    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("\u94a8\u821f\u548c\u5c4f\u853d\u7f51");
    model.component("comp1").selection("box1").set("entitydim", 2);
    model.component("comp1").selection("box1").set("xmin", -50);
    model.component("comp1").selection("box1").set("xmax", 50);
    model.component("comp1").selection("box1").set("ymin", -35);
    model.component("comp1").selection("box1").set("ymax", 35);
    model.component("comp1").selection("box1").set("zmin", -10);
    model.component("comp1").selection("box1").set("zmax", 80);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u524d\u56db\u5206\u4e4b\u4e00\u5706");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(1, 4);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u6837\u54c1\u652f\u67b6\u80cc\u9762");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(9, 10, 12, 44, 52);
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u6c89\u79ef\u8868\u9762");
    model.component("comp1").selection("dif1").set("entitydim", 2);
    model.component("comp1").selection("dif1").set("add", new String[]{"sel1"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"box1", "sel2", "sel3"});

    model.component("comp1").physics("fmf").prop("IntegrationProperty").set("IntegrationResolution", 4096);
    model.component("comp1").physics("fmf").prop("Compute").set("ComputeN", false);
    model.component("comp1").physics("fmf").prop("Compute").set("ComputeP", false);
    model.component("comp1").physics("fmf").feature("fmfp1").setIndex("Mn_G", "Mn0", 0);
    model.component("comp1").physics("fmf").feature("st1").set("T", "Tamb");
    model.component("comp1").physics("fmf").feature("wall1").set("BCType", "Deposition");
    model.component("comp1").physics("fmf").feature("wall1").setIndex("rho_film", "rho0", 0);
    model.component("comp1").physics("fmf").create("evap1", "Evaporation", 2);
    model.component("comp1").physics("fmf").feature("evap1").selection().set(41);
    model.component("comp1").physics("fmf").feature("evap1").setIndex("pvap", "pvap", 0);
    model.component("comp1").physics("fmf").create("st2", "SurfaceTemperature", 2);
    model.component("comp1").physics("fmf").feature("st2").selection().named("ball1");
    model.component("comp1").physics("fmf").feature("st2").set("T", "Tevap");

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("ball1");
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size2").selection().named("geom1_sel4");
    model.component("comp1").mesh("mesh1").feature("size2").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature("size3").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size3").selection().named("geom1_sel5");
    model.component("comp1").mesh("mesh1").feature("size3").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("size3").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size3").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size3").set("hmax", 3);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().remaining();
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,6,60)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u5165\u5c04\u5206\u5b50\u901a\u91cf (fmf)");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("expr", "fmf.Gtot");
    model.result("pg1").feature("surf1").set("resolution", "norefine");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().named("dif1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u819c\u539a");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "fmf.h_film_G");
    model.result("pg2").feature("surf1").set("descr", "\u819c\u539a");
    model.result("pg2").feature("surf1").set("unit", "nm");
    model.result("pg2").feature("surf1").set("colortable", "Viridis");
    model.result("pg2").feature("surf1").set("resolution", "norefine");
    model.result("pg2").run();
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").selection().named("geom1_sel5");
    model.result().dataset("surf1").set("param", "xy");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u6837\u54c1\u4e0a\u7684\u819c\u539a");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "fmf.h_film_G");
    model.result("pg3").feature("surf1").set("descr", "\u819c\u539a");
    model.result("pg3").feature("surf1").set("unit", "nm");
    model.result("pg3").feature("surf1").set("colortable", "Viridis");
    model.result("pg3").feature("surf1").set("resolution", "norefine");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u819c\u539a\u4e0e\u65f6\u95f4\u7684\u5173\u7cfb");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").selection().set(19);
    model.result("pg4").feature("ptgr1").set("expr", "fmf.h_film_G");
    model.result("pg4").feature("ptgr1").set("descr", "\u819c\u539a");
    model.result("pg4").feature("ptgr1").set("unit", "nm");
    model.result("pg4").run();

    model.title("\u84b8\u53d1\u5668");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u8ba1\u7b97\u70ed\u84b8\u53d1\u91d1\u819c\u7684\u539a\u5ea6\uff0c\u5e76\u8ba1\u7b97\u8154\u58c1\u548c\u6837\u54c1\u4e0a\u7684\u6c89\u79ef\u819c\u539a\u5ea6\u3002\n\n\u672c\u4f8b\u81f3\u5c11\u9700\u8981 6\u00a0GB \u5185\u5b58\u624d\u80fd\u8fd0\u884c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("evaporator.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
