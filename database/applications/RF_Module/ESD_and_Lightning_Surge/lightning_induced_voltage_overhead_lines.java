/*
 * lightning_induced_voltage_overhead_lines.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:34 by COMSOL 6.3.0.290. */
public class lightning_induced_voltage_overhead_lines {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\ESD_and_Lightning_Surge");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("temw", "TransientElectromagneticWaves", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/temw", true);

    model.param().set("W_air", "100[m]");
    model.param().descr("W_air", "\u7a7a\u6c14\u57df\u5bbd\u5ea6");
    model.param().set("D_air", "60[m]");
    model.param().descr("D_air", "\u7a7a\u6c14\u57df\u6df1\u5ea6");
    model.param().set("H_air", "80[m]");
    model.param().descr("H_air", "\u7a7a\u6c14\u57df\u9ad8\u5ea6");
    model.param().set("H_soil", "10[m]");
    model.param().descr("H_soil", "\u571f\u58e4\u5c42\u539a\u5ea6");
    model.param().set("L_wire", "25[m]");
    model.param().descr("L_wire", "\u5bfc\u7ebf\u957f\u5ea6");
    model.param().set("H_wire", "0.5[m]");
    model.param().descr("H_wire", "\u5bfc\u7ebf\u9ad8\u5ea6");
    model.param().set("r_wire", "0.25[mm]");
    model.param().descr("r_wire", "\u5bfc\u7ebf\u534a\u5f84");
    model.param().set("LDx", "7.5[m]");
    model.param().descr("LDx", "\u5bfc\u7ebf\u4e0e\u96f7\u51fb\u70b9\u4e4b\u95f4\u7684 X \u8ddd\u79bb");
    model.param().set("LDy", "1.5[m]");
    model.param().descr("LDy", "\u5bfc\u7ebf\u4e0e\u96f7\u51fb\u70b9\u4e4b\u95f4\u7684 Y \u8ddd\u79bb");
    model.param().set("ds", "0.1[m]");
    model.param().descr("ds", "\u7ec6\u5bfc\u7ebf\u57df\u7f51\u683c\u5927\u5c0f");
    model.param().set("vr", "125[m/us]");
    model.param().descr("vr", "\u7535\u6d41\u901f\u5ea6");
    model.param().set("epsilonr", "log(1/0.23)/log(ds/r_wire)");
    model.param().descr("epsilonr", "\u4fee\u6b63\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570");
    model.param().set("mur", "1/epsilonr");
    model.param().descr("mur", "\u4fee\u6b63\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.param().set("C_term", "20[pF]");
    model.param().descr("C_term", "\u7aef\u63a5\u7535\u5bb9");
    model.param().set("R_term", "430[ohm]");
    model.param().descr("R_term", "\u7aef\u63a5\u7535\u963b");
    model.param().set("S_capacitor", "0.1[m]");
    model.param().descr("S_capacitor", "\u5e73\u884c\u677f\u7535\u5bb9\u5668\u7684\u95f4\u8ddd");
    model.param().set("W_capacitor", "0.5[m]");
    model.param().descr("W_capacitor", "\u7535\u5bb9\u5668\u5bbd\u5ea6");
    model.param().set("L_capacitor", "C_term*S_capacitor/epsilon0_const/W_capacitor");
    model.param().descr("L_capacitor", "\u7535\u5bb9\u5668\u957f\u5ea6");
    model.param().set("angle", "90[degree]");
    model.param().descr("angle", "\u5bfc\u7ebf\u503e\u89d2");
    model.param().set("sigma_soil", "0.06[S/m]");
    model.param().descr("sigma_soil", "\u571f\u58e4\u7535\u5bfc\u7387");
    model.param().set("epsilonr_soil", "10");
    model.param().descr("epsilonr_soil", "\u571f\u58e4\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("funcname", "ic");
    model.func("int1")
         .set("table", new String[][]{{"0", "0"}, 
         {"2.564e-01", "1.036e-02"}, 
         {"1.326e+00", "3.274e-02"}, 
         {"6.971e+00", "1.483e-01"}, 
         {"1.265e+01", "3.035e-01"}, 
         {"1.475e+01", "4.407e-01"}, 
         {"1.537e+01", "5.605e-01"}, 
         {"1.721e+01", "6.659e-01"}, 
         {"1.954e+01", "7.843e-01"}, 
         {"2.156e+01", "9.048e-01"}, 
         {"2.367e+01", "9.698e-01"}, 
         {"2.565e+01", "9.857e-01"}, 
         {"3.793e+01", "1.035e+00"}, 
         {"4.193e+01", "1.041e+00"}, 
         {"5.022e+01", "1.071e+00"}, 
         {"5.832e+01", "1.096e+00"}, 
         {"6.535e+01", "1.098e+00"}, 
         {"7.759e+01", "1.104e+00"}, 
         {"9.215e+01", "1.111e+00"}, 
         {"1.064e+02", "1.113e+00"}, 
         {"1.166e+02", "1.107e+00"}, 
         {"1.290e+02", "1.088e+00"}, 
         {"1.431e+02", "1.060e+00"}, 
         {"1.476e+02", "1.054e+00"}, 
         {"1.531e+02", "1.053e+00"}, 
         {"1.656e+02", "1.036e+00"}, 
         {"1.793e+02", "1.026e+00"}, 
         {"1.877e+02", "1.022e+00"}, 
         {"1.986e+02", "9.964e-01"}, 
         {"2.171e+02", "9.806e-01"}, 
         {"2.331e+02", "9.742e-01"}, 
         {"2.537e+02", "9.549e-01"}, 
         {"2.811e+02", "9.370e-01"}, 
         {"3.076e+02", "9.214e-01"}, 
         {"3.076e+02", "9.214e-01"}, 
         {"3.359e+02", "9.216e-01"}, 
         {"3.713e+02", "8.929e-01"}, 
         {"3.859e+02", "9.003e-01"}, 
         {"4.314e+02", "8.753e-01"}});
    model.func("int1").set("interp", "piecewisecubic");
    model.func("int1").setIndex("fununit", "A", 0);
    model.func("int1").setIndex("argunit", "ns", 0);

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"W_air", "D_air", "H_air"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "0", "-H_soil"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "H_soil", 0);
    model.component("comp1").geom("geom1").run("blk1");

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(2);
    model.component("comp1").view("view1").hideObjects("hide1").set("blk1", 4, 5, 7);

    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"W_air/2-L_wire/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("ls1").setIndex("coord1", "D_air/2", 1);
    model.component("comp1").geom("geom1").feature("ls1").setIndex("coord1", "H_wire", 2);
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"W_air/2+L_wire/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("ls1").setIndex("coord2", "D_air/2", 1);
    model.component("comp1").geom("geom1").feature("ls1").setIndex("coord2", "H_wire", 2);
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls2")
         .set("coord1", new String[]{"W_air/2+L_wire/2+LDx", "0", "0"});
    model.component("comp1").geom("geom1").feature("ls2").setIndex("coord1", "D_air/2+LDy", 1);
    model.component("comp1").geom("geom1").feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls2")
         .set("coord2", new String[]{"W_air/2+L_wire/2+LDx+(H_air-H_soil)*0.8*cos(angle)", "0", "0"});
    model.component("comp1").geom("geom1").feature("ls2").setIndex("coord2", "D_air/2+LDy", 1);
    model.component("comp1").geom("geom1").feature("ls2").setIndex("coord2", "(H_air-H_soil)*0.8*sin(angle)", 2);
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"L_wire", "2*ds", "2*ds"});
    model.component("comp1").geom("geom1").feature("blk2").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"W_air/2", "D_air/2", "0"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("pos", "H_wire", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("layerfront", true);
    model.component("comp1").geom("geom1").feature("blk2").setIndex("layer", "ds", 0);
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"L_wire", "W_capacitor"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"W_air/2", "D_air/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("layer", "L_capacitor", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("layerright", true);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "S_capacitor", 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "H_wire-ds", 1);
    model.component("comp1").geom("geom1").feature("ext1").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("ext1", 3, 4);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7a7a\u6c14");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material("mat1").selection().set(2, 3, 4, 9, 10);
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u571f\u58e4");
    model.component("comp1").material("mat2").selection().set(1);
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"epsilonr_soil"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma_soil"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u4fee\u6b63\u6750\u6599");
    model.component("comp1").material("mat3").selection().set(5, 6, 7, 8);
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"epsilonr"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", new String[]{"mur"});
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").physics("temw").prop("ShapeProperty").set("order_magneticvectorpotential", 1);
    model.component("comp1").physics("temw").create("sctr1", "Scattering", 2);
    model.component("comp1").physics("temw").feature("sctr1").selection().set(1, 2, 3, 4, 5, 7, 8, 9, 58, 59);
    model.component("comp1").physics("temw").create("pec2", "PerfectElectricConductor", 2);
    model.component("comp1").physics("temw").feature("pec2").selection().set(12, 13, 15, 17, 24, 42, 45, 53, 54, 56);
    model.component("comp1").physics("temw").create("lelement1", "LumpedElement", 2);
    model.component("comp1").physics("temw").feature("lelement1")
         .label("\u96c6\u603b\u5143\u4ef6\uff0c\u8fdc\u7aef");
    model.component("comp1").physics("temw").feature("lelement1").selection().set(10);
    model.component("comp1").physics("temw").feature("lelement1").set("Zelement", "R_term");
    model.component("comp1").physics("temw").create("lelement2", "LumpedElement", 2);
    model.component("comp1").physics("temw").feature("lelement2")
         .label("\u96c6\u603b\u5143\u4ef6\uff0c\u8fd1\u7aef");
    model.component("comp1").physics("temw").feature("lelement2").selection().set(52);
    model.component("comp1").physics("temw").feature("lelement2").set("Zelement", "R_term");
    model.component("comp1").physics("temw").create("constr1", "PointwiseConstraint", 1);
    model.component("comp1").physics("temw").feature("constr1").selection().set(35);
    model.component("comp1").physics("temw").feature("constr1").set("constraintExpression", "0-tAx");
    model.component("comp1").physics("temw").feature().duplicate("constr2", "constr1");
    model.component("comp1").physics("temw").feature("constr2").set("constraintExpression", "0-tAy");
    model.component("comp1").physics("temw").feature().duplicate("constr3", "constr2");
    model.component("comp1").physics("temw").feature("constr3").set("constraintExpression", "0-tAz");
    model.component("comp1").physics("temw").create("edc1", "EdgeCurrent", 1);
    model.component("comp1").physics("temw").feature("edc1").selection().set(101);
    model.component("comp1").physics("temw").feature("edc1").set("Ie", "ic(t-s1[m]/vr)");
    model.component("comp1").physics("temw").prop("MeshControl")
         .set("PhysicsControlledMeshMaximumElementSize", "10[m]");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature().move("map1", 1);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(17, 20, 24, 27);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(5, 6, 7, 8);
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmax", "3*ds");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmin", "ds");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(101);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "0.5[m]");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().set(10, 52);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmax", "S_capacitor*0.6");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("time").set("tunit", "ns");
    model.study("std1").feature("time").set("tlist", "range(0,2,300)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsgenalpha", "manual");
    model.sol("sol1").feature("t1").set("timestepgenalpha", "0.5[ns]");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u573a");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "1");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().set(1, 2, 3, 6, 8, 9, 12, 42, 58, 59);
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").create("sel1", "Selection");
    model.result("pg1").feature("line1").feature("sel1").selection().set(101);
    model.result("pg1").run();
    model.result("pg1").create("line2", "Line");
    model.result("pg1").feature("line2").create("sel1", "Selection");
    model.result("pg1").feature("line2").feature("sel1").selection().set(35);
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").set("showlegends", false);

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").set("titletype", "none");
    model.result("pg1").setIndex("looplevel", 101, 0);
    model.result("pg1").run();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1")
         .importData("lightning_induced_voltage_overhead_lines_measured_voltage_remote_end.txt");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2")
         .importData("lightning_induced_voltage_overhead_lines_measured_voltage_close_end.txt");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u611f\u5e94\u7535\u538b");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u65f6\u95f4\uff0cns");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u611f\u5e94\u7535\u538b\uff0cV");
    model.result("pg2").set("legendpos", "lowerleft");
    model.result("pg2").create("tblp1", "Table");
    model.result("pg2").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg2").feature("tblp1").set("linewidth", "preference");
    model.result("pg2").feature("tblp1").set("linemarker", "cycle");
    model.result("pg2").feature("tblp1").set("legend", true);
    model.result("pg2").feature("tblp1").set("legendmethod", "manual");
    model.result("pg2").feature("tblp1").setIndex("legends", "Measurement, Remote End", 0);
    model.result("pg2").feature("tblp1").setIndex("legends", "\u6d4b\u91cf\uff0c\u8fdc\u7aef", 0);
    model.result("pg2").run();
    model.result("pg2").create("tblp2", "Table");
    model.result("pg2").feature("tblp2").set("markerpos", "datapoints");
    model.result("pg2").feature("tblp2").set("linewidth", "preference");
    model.result("pg2").feature("tblp2").set("table", "tbl2");
    model.result("pg2").feature("tblp2").set("linemarker", "cycle");
    model.result("pg2").feature("tblp2").set("legend", true);
    model.result("pg2").feature("tblp2").set("legendmethod", "manual");
    model.result("pg2").feature("tblp2").setIndex("legends", "Measurement, Close End", 0);
    model.result("pg2").feature("tblp2").setIndex("legends", "\u6d4b\u91cf\uff0c\u8fd1\u7aef", 0);
    model.result("pg2").run();
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "temw.Velement_1", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "\u4eff\u771f\uff0c\u8fdc\u7aef", 0);
    model.result("pg2").feature("glob1").setIndex("expr", "temw.Velement_2", 1);
    model.result("pg2").feature("glob1").setIndex("descr", "\u4eff\u771f\uff0c\u8fd1\u7aef", 1);
    model.result("pg2").run();

    model.title("\u67b6\u7a7a\u7ebf\u5728\u6709\u635f\u5730\u9762\u4e0a\u7684\u96f7\u7535\u611f\u5e94\u7535\u538b");

    model
         .description("\u672c\u6a21\u578b\u8ba1\u7b97\u67b6\u7a7a\u7ebf\u5728\u6709\u635f\u5730\u9762\u4e0a\u56e0\u96f7\u7535\u4f5c\u7528\u800c\u4ea7\u751f\u7684\u611f\u5e94\u7535\u538b\uff0c\u5176\u4e2d\u8003\u8651\u4e86\u96f7\u7535\u901a\u9053\u7684\u503e\u659c\u89d2\u5ea6\u548c\u571f\u58e4\u7535\u5bfc\u7387\u7b49\u53c2\u6570\uff0c\u4fbf\u4e8e\u76f4\u63a5\u5206\u6790\u5b83\u4eec\u7684\u5f71\u54cd\u3002\u8ba1\u7b97\u5f97\u5230\u7684\u611f\u5e94\u7535\u538b\u4e0e\u5b9e\u9a8c\u6d4b\u91cf\u7ed3\u679c\u9ad8\u5ea6\u4e00\u81f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("lightning_induced_voltage_overhead_lines.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
