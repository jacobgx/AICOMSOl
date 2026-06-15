/*
 * cavity_radiation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:36 by COMSOL 6.3.0.290. */
public class cavity_radiation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");
    model.component("comp1").physics().create("rad", "SurfaceToSurfaceRadiation", "geom1");

    model.component("comp1").multiphysics().create("htrad1", "HeatTransferWithSurfaceToSurfaceRadiation", 1);
    model.component("comp1").multiphysics("htrad1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("htrad1").set("Rad_physics", "rad");
    model.component("comp1").multiphysics("htrad1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/rad", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/htrad1", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{4, 1});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{0, -1});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{1, 3});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{4, 0});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new int[]{5, 1});
    model.component("comp1").geom("geom1").feature("r3").set("rot", "atan(3/4)[rad]");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u94dc");
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"400"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"8700"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"385"});

    model.component("comp1").physics("ht").feature("init1").set("Tinit", "300[K]");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(2);
    model.component("comp1").physics("ht").feature("hf1").set("q0_input", "1000[W/m^2]");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf2").selection().set(12);
    model.component("comp1").physics("ht").feature("hf2").set("q0_input", "2000[W/m^2]");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(4);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "300[K]");
    model.component("comp1").physics("ht").create("inse1", "ThermalInsulationEdge", 0);
    model.component("comp1").physics("ht").feature("inse1").selection().set(3, 6, 7);
    model.component("comp1").physics("rad").selection().set(5, 6, 9);
    model.component("comp1").physics("rad").feature("dsurf1").set("Tamb", "300[K]");
    model.component("comp1").physics("rad").feature("dsurf1").set("epsilon_rad_mat", "userdef");
    model.component("comp1").physics("rad").feature("dsurf1").set("epsilon_rad", 0.8);
    model.component("comp1").physics("rad").create("dsurf2", "DiffuseSurface", 1);
    model.component("comp1").physics("rad").feature("dsurf2").selection().set(5);
    model.component("comp1").physics("rad").feature("dsurf2").set("Tamb", "300[K]");
    model.component("comp1").physics("rad").feature("dsurf2").set("epsilon_rad_mat", "userdef");
    model.component("comp1").physics("rad").feature("dsurf2").set("epsilon_rad", 0.4);
    model.component("comp1").physics("rad").create("dsurf3", "DiffuseSurface", 1);
    model.component("comp1").physics("rad").feature("dsurf3").selection().set(9);
    model.component("comp1").physics("rad").feature("dsurf3").set("Tamb", "300[K]");
    model.component("comp1").physics("rad").feature("dsurf3").set("epsilon_rad_mat", "userdef");
    model.component("comp1").physics("rad").feature("dsurf3").set("epsilon_rad", 0.6);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().geom("geom1");
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().set(5, 6, 9);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", 0.05);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").selection().set(3, 6, 7);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").set("hmax", 0.01);
    model.component("comp1").mesh("mesh1").run("edg1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").set("method", "af");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u8868\u9762\u8f90\u5c04\u5ea6 (rad)");
    model.result("pg2").feature().create("line1", "Line");
    model.result("pg2").feature("line1").label("\u4e0a\u4fa7\u8f90\u5c04\u5ea6");
    model.result("pg2").feature("line1").set("showsolutionparams", "on");
    model.result("pg2").feature("line1").set("expr", "rad.Ju");
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("smooth", "internal");
    model.result("pg2").feature("line1").set("inheritdeformscale", false);
    model.result("pg2").feature("line1").set("showsolutionparams", "on");
    model.result("pg2").feature("line1").set("data", "parent");
    model.result("pg2").feature("line1").feature().create("def1", "Deform");
    model.result("pg2").feature("line1").feature("def1")
         .set("expr", new String[]{"nx/sqrt(tremetric)", "ny/sqrt(tremetric)"});
    model.result("pg2").feature("line1").feature("def1").set("scale", "0.1");
    model.result("pg2").feature().create("line2", "Line");
    model.result("pg2").feature("line2").label("\u4e0b\u4fa7\u8f90\u5c04\u5ea6");
    model.result("pg2").feature("line2").set("showsolutionparams", "on");
    model.result("pg2").feature("line2").set("expr", "rad.Jd");
    model.result("pg2").feature("line2").set("linetype", "tube");
    model.result("pg2").feature("line2").set("smooth", "internal");
    model.result("pg2").feature("line2").set("showsolutionparams", "on");
    model.result("pg2").feature("line2").set("data", "parent");
    model.result("pg2").feature("line2").set("inheritplot", "line1");
    model.result("pg2").feature("line2").feature().create("def1", "Deform");
    model.result("pg2").feature("line2").feature("def1")
         .set("expr", new String[]{"-nx/sqrt(tremetric)", "-ny/sqrt(tremetric)"});
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u6e29\u5ea6\u66f2\u7ebf vs. \u5f27\u957f");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").selection().set(6);
    model.result("pg3").feature("lngr1").set("xdata", "reversedarc");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u8868\u9762\u8f90\u5c04\u5ea6\u66f2\u7ebf vs. \u5f27\u957f");
    model.result("pg4").run();
    model.result("pg4").feature("lngr1").set("expr", "rad.J");
    model.result("pg4").feature("lngr1").set("descr", "\u8868\u9762\u8f90\u5c04\u5ea6");
    model.result("pg4").run();
    model.result().numerical().create("int1", "IntLine");
    model.result().numerical("int1").set("intsurface", true);
    model.result().numerical("int1").selection().set(5);
    model.result().numerical("int1").set("expr", new String[]{"ht.ndflux"});
    model.result().numerical("int1").set("descr", new String[]{"\u6cd5\u5411\u4f20\u5bfc\u70ed\u901a\u91cf"});
    model.result().numerical("int1").set("unit", new String[]{"W/m"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u7ebf\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().numerical().create("int2", "IntLine");
    model.result().numerical("int2").set("intsurface", true);
    model.result().numerical("int2").selection().set(9);
    model.result().numerical("int2").setIndex("expr", "T/3", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u7ebf\u79ef\u5206 2");
    model.result().numerical("int2").set("table", "tbl2");
    model.result().numerical("int2").setResult();
    model.result().numerical().create("int3", "IntLine");
    model.result().numerical("int3").set("intsurface", true);
    model.result().numerical("int3").selection().set(6);
    model.result().numerical("int3").setIndex("expr", "T/5", 0);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u7ebf\u79ef\u5206 3");
    model.result().numerical("int3").set("table", "tbl3");
    model.result().numerical("int3").setResult();
    model.result("pg1").run();

    model.title("\u7a7a\u8154\u8f90\u5c04");

    model
         .description("\u4e09\u4e2a\u8868\u9762\u5f62\u6210\u7a7a\u8154\uff0c\u70ed\u901a\u91cf\u8bbe\u7f6e\u4e8e\u4e24\u4e2a\u5916\u90e8\u8fb9\u754c\u4e0a\uff0c\u6e29\u5ea6\u8bbe\u7f6e\u4e8e\u7b2c\u4e09\u4e2a\u8fb9\u754c\u3002\u8ba1\u7b97\u7684\u7ed3\u679c\u4e0e\u7406\u8bba\u7ed3\u679c\u505a\u4e86\u6bd4\u8f83\u3002");

    model.label("cavity_radiation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
