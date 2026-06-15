/*
 * rotating_plate.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:16 by COMSOL 6.3.0.290. */
public class rotating_plate {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Molecular_Flow_Module\\Benchmarks");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("fmf", "FreeMolecularFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/fmf", true);

    model.param().set("theta", "90[deg]");
    model.param().descr("theta", "\u677f\u4e0e\u6c34\u5e73\u9762\u7684\u89d2\u5ea6");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{1.1, 0.1});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new double[]{0, -0.05});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", -0.025, 1);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("pt2", "Point");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", 0.025, 1);
    model.component("comp1").geom("geom1").run("pt2");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{0.05, 0.0025});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{1, 0});
    model.component("comp1").geom("geom1").feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("r2").set("rot", "theta");
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("r2");
    model.component("comp1").geom("geom1").runPre("fin");

    model.func().create("int1", "Interpolation");
    model.func("int1").label("Rad_G");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "RadiationAnalogyG.txt");
    model.func("int1").importData();
    model.func("int1").set("funcname", "Rad_G");
    model.func().create("int2", "Interpolation");
    model.func("int2").label("Rad_p");
    model.func("int2").set("source", "file");
    model.func("int2").set("filename", "RadiationAnalogyP.txt");
    model.func("int2").importData();
    model.func("int2").set("funcname", "Rad_p");
    model.func().create("int3", "Interpolation");
    model.func("int3").label("Rad_n");
    model.func("int3").set("source", "file");
    model.func("int3").set("filename", "RadiationAnalogyN.txt");
    model.func("int3").importData();
    model.func("int3").set("funcname", "Rad_n");

    model.component("comp1").cpl().create("aveop1", "Average");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().set(6);

    model.component("comp1").physics("fmf").prop("IntegrationProperty").set("IntegrationResolution", 4096);
    model.component("comp1").physics("fmf").create("tv1", "TotalVacuum", 1);
    model.component("comp1").physics("fmf").feature("tv1").selection().set(1, 2, 4, 5, 10);
    model.component("comp1").physics("fmf").create("res1", "Reservoir", 1);
    model.component("comp1").physics("fmf").feature("res1").selection().set(3);
    model.component("comp1").physics("fmf").feature("res1").setIndex("p0", "1e-5[mbar]", 0);

    model.component("comp1").mesh("mesh1").create("sca1", "Scale");
    model.component("comp1").mesh("mesh1").feature("sca1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("sca1").set("scale", 0.1);
    model.component("comp1").mesh("mesh1").feature("sca1").selection().set(3, 6, 9);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 1);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "theta", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "rad", 0);
    model.study("std1").feature("param").setIndex("pname", "theta", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "rad", 0);
    model.study("std1").feature("param").setIndex("plistarr", "5 range(10,10,90)", 0);
    model.study("std1").feature("param").setIndex("punit", "deg", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u5165\u5c04\u5206\u5b50\u901a\u91cf (fmf)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 10, 0);
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
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 10, 0);
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
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 10, 0);
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
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().set(6);
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").run();
    model.result("pg4").label("G \u5747\u5300\u6027");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("legendpos", "lowerright");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "aveop1(G)", 0);
    model.result("pg5").feature("glob1")
         .setIndex("descr", "\u6d41\u5165\u901a\u91cf\uff08\u5206\u5b50\u6d41\uff09", 0);
    model.result("pg5").feature("glob1").setIndex("expr", "Rad_G(theta*180/pi)", 1);
    model.result("pg5").feature("glob1").setIndex("descr", "\u6d41\u5165\u901a\u91cf\uff08\u8f90\u5c04\uff09", 1);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").label("G");
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u6570\u5bc6\u5ea6\u677f");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").setIndex("expr", "aveop1(fmf.nin_G)", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "\u6d41\u5165 n\uff08\u5206\u5b50\u6d41\uff09", 0);
    model.result("pg6").feature("glob1").setIndex("expr", "aveop1(fmf.nout_G)", 1);
    model.result("pg6").feature("glob1").setIndex("descr", "\u6d41\u51fa n\uff08\u5206\u5b50\u6d41\uff09", 1);
    model.result("pg6").feature("glob1").setIndex("expr", "aveop1(fmf.n_G)", 2);
    model.result("pg6").feature("glob1").setIndex("descr", "\u603b\u8ba1 n\uff08\u5206\u5b50\u6d41\uff09", 2);
    model.result("pg6").feature("glob1").setIndex("expr", "Rad_n(theta*180/pi)", 3);
    model.result("pg6").feature("glob1").setIndex("descr", "\u603b\u8ba1 n\uff08\u8f90\u5c04\uff09", 3);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u538b\u529b\u677f");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").setIndex("expr", "aveop1(fmf.pin_G)", 0);
    model.result("pg7").feature("glob1")
         .setIndex("descr", "\u6d41\u5165\u538b\u529b\uff08\u5206\u5b50\u6d41\uff09", 0);
    model.result("pg7").feature("glob1").setIndex("expr", "aveop1(fmf.pout_G)", 1);
    model.result("pg7").feature("glob1")
         .setIndex("descr", "\u6d41\u51fa\u538b\u529b\uff08\u5206\u5b50\u6d41\uff09", 1);
    model.result("pg7").feature("glob1").setIndex("expr", "aveop1(fmf.p_G)", 2);
    model.result("pg7").feature("glob1").setIndex("descr", "\u603b\u538b\uff08\u5206\u5b50\u6d41\uff09", 2);
    model.result("pg7").feature("glob1").setIndex("expr", "Rad_p(theta*180/pi)", 3);
    model.result("pg7").feature("glob1").setIndex("descr", "\u603b\u538b\uff08\u8f90\u5c04\uff09", 3);
    model.result("pg7").run();
    model.result("pg7").run();

    model.title("\u5355\u5411\u5206\u5b50\u6d41\u4e2d\u7684\u65cb\u8f6c\u677f");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u5728\u4e00\u4e2a\u9ad8\u5ea6\u5b9a\u5411\u7684\u5206\u5b50\u6d41\u4e2d\u65cb\u8f6c\u7684\u5e73\u677f\u8868\u9762\u7684\u7c92\u5b50\u901a\u91cf\u3001\u6570\u5bc6\u5ea6\u548c\u538b\u529b\u3002\u83b7\u5f97\u7684\u7ed3\u679c\u4e0e\u5176\u4ed6\u8ba1\u7b97\u5206\u5b50\u6d41\u7684\u8fd1\u4f3c\u65b9\u6cd5\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();

    model.label("rotating_plate.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
