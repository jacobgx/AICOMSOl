/*
 * ion_implanter.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:18 by COMSOL 6.3.0.290. */
public class ion_implanter {

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
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/fmf", true);

    model.component("comp1").geom("geom1").insertFile("ion_implanter_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("comsel1");

    model.param().set("pumpspeedcryo", "12000[l/s]");
    model.param().descr("pumpspeedcryo", "\u4f4e\u6e29\u6cf5\u7684\u6cf5\u901f");
    model.param().set("pumpspeedturbo", "1500[l/s]");
    model.param().descr("pumpspeedturbo", "\u6da1\u8f6e\u6cf5\u7684\u6cf5\u901f");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(6, 33, 103);
    model.component("comp1").selection("sel1").label("\u675f\u7ebf");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").cpl("aveop1").selection().named("sel1");

    model.component("comp1").physics("fmf").feature("fmfp1").setIndex("Mn_G", "0.002[kg/mol]", 0);
    model.component("comp1").physics("fmf").create("wall2", "Wall", 2);
    model.component("comp1").physics("fmf").feature("wall2").selection().set(42);
    model.component("comp1").physics("fmf").feature("wall2").set("BCType", "OutgassingWall");
    model.component("comp1").physics("fmf").feature("wall2").set("BoundaryCondition", "NumberOfSCCM");
    model.component("comp1").physics("fmf").feature("wall2").setIndex("sccmmfr", 30, 0);
    model.component("comp1").physics("fmf").create("pmp1", "VacuumPump", 2);
    model.component("comp1").physics("fmf").feature("pmp1").set("SpecifyPump", "PumpSpeed");
    model.component("comp1").physics("fmf").feature("pmp1").selection().set(55);
    model.component("comp1").physics("fmf").feature("pmp1").setIndex("pspeed", "pumpspeedturbo", 0);
    model.component("comp1").physics("fmf").create("pmp2", "VacuumPump", 2);
    model.component("comp1").physics("fmf").feature("pmp2").set("SpecifyPump", "PumpSpeed");
    model.component("comp1").physics("fmf").feature("pmp2").selection().set(8);
    model.component("comp1").physics("fmf").feature("pmp2").setIndex("pspeed", "pumpspeedcryo", 0);
    model.component("comp1").physics("fmf").feature().duplicate("pmp3", "pmp2");
    model.component("comp1").physics("fmf").feature("pmp3").selection().set(25);
    model.component("comp1").physics("fmf").feature().duplicate("pmp4", "pmp3");
    model.component("comp1").physics("fmf").feature("pmp4").selection().set(70);
    model.component("comp1").physics("fmf").feature().duplicate("pmp5", "pmp4");
    model.component("comp1").physics("fmf").feature("pmp5").selection().set(33);
    model.component("comp1").physics("fmf").create("ndr1", "NumberDensityReconEdge", 1);
    model.component("comp1").physics("fmf").feature("ndr1").selection().named("sel1");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", 0.005);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().all();
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "theta", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "rad", 0);
    model.study("std1").feature("param").setIndex("pname", "theta", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "rad", 0);
    model.study("std1").feature("param").setIndex("punit", "deg", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(0,20,60)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u5165\u5c04\u5206\u5b50\u901a\u91cf (fmf)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 4, 0);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("expr", "fmf.Gtot");
    model.result("pg1").feature("surf1").set("resolution", "norefine");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u603b\u6570\u5bc6\u5ea6 (fmf)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 4, 0);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "fmf.ntot");
    model.result("pg2").feature("surf1").set("resolution", "norefine");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u603b\u538b (fmf)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 4, 0);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "fmf.ptot");
    model.result("pg3").feature("surf1").set("resolution", "norefine");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().dataset().duplicate("dset3", "dset2");
    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().named("geom1_comsel1");
    model.result("pg1").run();
    model.result("pg1").set("data", "dset3");
    model.result("pg2").run();
    model.result("pg2").set("data", "dset3");
    model.result("pg3").run();
    model.result("pg3").set("data", "dset3");
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 3, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 4, 0);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u6cbf\u675f\u7ebf\u7684\u6570\u5bc6\u5ea6");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("legendpos", "lowerright");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().named("sel1");
    model.result("pg4").feature("lngr1").set("expr", "fmf.ntot");
    model.result("pg4").feature("lngr1").set("descr", "\u603b\u6570\u5bc6\u5ea6");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("resolution", "norefine");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u5e73\u5747\u6570\u5bc6\u5ea6 vs. \u6676\u5706\u89d2\u5ea6");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u79bb\u5b50\u675f\u4e0e\u6676\u5706\u7684\u5939\u89d2 (deg)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u5e73\u5747\u6570\u5bc6\u5ea6 (1/m<sup>3</sup>)");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "aveop1(fmf.ntot)", 0);
    model.result("pg5").feature("glob1").setIndex("unit", "1/(m^3)", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u5e73\u5747\u6570\u5bc6\u5ea6", 0);
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "theta");
    model.result("pg5").run();

    model.title("\u79bb\u5b50\u6ce8\u5165\u771f\u7a7a\u7cfb\u7edf\u4e2d\u7684\u5206\u5b50\u6d41");

    model
         .description("\u672c\u4f8b\u8003\u8651\u79bb\u5b50\u6ce8\u5165\u7cfb\u7edf\u7684\u8bbe\u8ba1\u3002\u6b64\u7cfb\u7edf\u7684\u4e00\u4e2a\u5173\u952e\u8bbe\u8ba1\u9700\u6c42\u662f\uff0c\u5728\u79bb\u5b50\u675f\u65b9\u5411\u4e0a\uff0c\u6392\u6c14\u5206\u5b50\u7684\u6570\u5bc6\u5ea6\u5fc5\u987b\u5f88\u5c0f\u3002\u672c\u4f8b\u8ba1\u7b97\u7cfb\u7edf\u5185\u4e00\u4e2a\u76ee\u6807\u6392\u6c14\u6676\u5706\u7ed5\u8f74\u65cb\u8f6c\u65f6\uff0c\u6cbf\u79bb\u5b50\u675f\u90e8\u5206\u8def\u5f84\u7684\u6570\u5bc6\u5ea6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("ion_implanter.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
