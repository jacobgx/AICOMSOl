/*
 * ceramic_water_filter_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:14 by COMSOL 6.3.0.290. */
public class ceramic_water_filter_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Porous_Media_Flow_Module\\Solute_Transport");

    model.param().set("r_filter", "3.3[cm]", "Filter radius");
    model.param().set("l_filter", "22[cm]", "Filter length");
    model.param().set("r_bowl", "4.8[cm]", "Housing bowl radius");
    model.param().set("l_bowl", "23.5[cm]", "Housing bowl length");
    model.param().set("r_head", "6[cm]", "Housing head radius");
    model.param().set("h_head", "4.5[cm]", "Housing head height");
    model.param().set("th_ceramics", "1.2[cm]", "Thickness ceramics");
    model.param().set("th_carbon", "1.5[cm]", "Thickness carbon");
    model.param().label("Parameters: Geometry");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("Filter domains");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("Fracture");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2")
         .set("pos", new String[]{"0", "0", "-(l_filter-th_ceramics-th_carbon)"});
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "r_filter-th_ceramics-th_carbon");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "(l_filter-th_ceramics-th_carbon)+0.01");
    model.component("comp1").geom("geom1").create("rev2", "Revolve");
    model.component("comp1").geom("geom1").feature("rev2").set("angle2", 90);
    model.component("comp1").geom("geom1").feature("rev2").set("axistype", "3d");
    model.component("comp1").geom("geom1").feature("rev2").set("pos3", new double[]{0.0138, 0, 0.01});
    model.component("comp1").geom("geom1").feature("rev2").selection("inputface").set("cyl2(1)", 4);
    model.component("comp1").geom("geom1").create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "0.075", 0);
    model.component("comp1").geom("geom1").feature("ext1").selection("inputface").set("rev2(1)", 11);
    model.component("comp1").geom("geom1").create("tor1", "Torus");
    model.component("comp1").geom("geom1").feature("tor1")
         .set("pos", new String[]{"-(r_filter+r_bowl)/2-0.0138", "0", "+0.01"});
    model.component("comp1").geom("geom1").feature("tor1").set("rot", 180);
    model.component("comp1").geom("geom1").feature("tor1").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").feature("tor1").set("rmaj", 0.0138);
    model.component("comp1").geom("geom1").feature("tor1").set("rmin", "r_filter-th_ceramics-th_carbon");
    model.component("comp1").geom("geom1").feature("tor1").set("angle", 90);
    model.component("comp1").geom("geom1").create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "0.03", 0);
    model.component("comp1").geom("geom1").feature("ext2").selection("inputface").set("tor1(1)", 6);
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "zy");
    model.component("comp1").geom("geom1").feature("wp1").set("rot", 90);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("table", new String[][]{{"0", "l_bowl"}, 
         {"r_bowl", "l_bowl"}, 
         {"r_bowl", "-0.01"}, 
         {"r_filter", "-0.01"}, 
         {"r_filter", "l_filter"}, 
         {"0", "l_filter"}});
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2")
         .set("table", new String[][]{{"0", "l_filter"}, 
         {"r_filter", "l_filter"}, 
         {"r_filter", "0"}, 
         {"r_filter-th_ceramics", "0"}, 
         {"r_filter-th_ceramics", "l_filter-th_ceramics"}, 
         {"0", "l_filter-th_ceramics"}});
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol3", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol3").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol3")
         .set("table", new String[][]{{"0", "l_filter-th_ceramics"}, 
         {"r_filter-th_ceramics", "l_filter-th_ceramics"}, 
         {"r_filter-th_ceramics", "0"}, 
         {"r_filter-th_ceramics-th_carbon", "0"}, 
         {"r_filter-th_ceramics-th_carbon", "l_filter-th_ceramics-th_carbon"}, 
         {"0", "l_filter-th_ceramics-th_carbon"}});
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", 0.015);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("point")
         .set("pol3(1)", 6);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("point")
         .set("pol2(1)", 4, 6);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("point")
         .set("pol1(1)", 4, 6);
    model.component("comp1").geom("geom1").create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "full");
    model.component("comp1").geom("geom1").feature("rev1").set("origfaces", false);
    model.component("comp1").geom("geom1").feature("rev1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").label("Modeling domains");
    model.component("comp1").geom("geom1").feature("uni1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("ext1", "ext2", "rev1");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", "r_head");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "h_head");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").label("Housing head");
    model.component("comp1").geom("geom1").feature("dif1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("dif1").set("propagatesel", false);
    model.component("comp1").geom("geom1").feature("dif1").set("keepsubtract", true);
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("cyl3");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("uni1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("Carbon");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("uni1(1)", 5);
    model.component("comp1").geom("geom1").feature("sel1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("Ceramics");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("uni1(1)", 4);
    model.component("comp1").geom("geom1").feature("sel2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("Laminar flow domains");
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"uni1"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"sel1", "sel2"});
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("Unfiltered water inlet");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("uni1(1)", 1);
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("Filtered water outlet");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("uni1(1)", 87);
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("planetype", "transformed");
    model.component("comp1").geom("geom1").feature("wp2").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("wp2").set("transdispl", new String[]{"0", "l_filter*0.5", "0"});
    model.component("comp1").geom("geom1").feature("wp2").set("transaxis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").feature("wp2").set("transrot", 125);
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cro1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .set("table", new String[][]{{"(r_filter-th_ceramics)/2", "l_filter*0.2-l_filter*0.5"}, {"-(r_filter-th_ceramics)*0.7", "l_filter*0.5-l_filter*0.5"}, {"-(r_filter-th_ceramics)*0.3", "l_filter*0.6-l_filter*0.5"}, {"-(r_filter-th_ceramics)*0.85", "l_filter*0.8-l_filter*0.5"}});
    model.component("comp1").geom("geom1").feature("wp2").geom().create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol2").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol2")
         .set("table", new String[][]{{"-(r_filter-th_ceramics)*0.7", "0"}, {"(r_filter-th_ceramics)*0.7", "l_filter*0.1"}, {"r_filter", "l_filter*0.3"}});
    model.component("comp1").geom("geom1").feature("wp2").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil1").set("radius", "2*th_carbon");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil1").selection("point")
         .set("pol1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil2").set("radius", "5*th_carbon");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil2").selection("point")
         .set("pol2(1)", 2);
    model.component("comp1").geom("geom1").create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").set("distance", new double[]{0.1, 0});
    model.component("comp1").geom("geom1").feature("ext3").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext3").set("includeinput", false);
    model.component("comp1").geom("geom1").feature("ext3").set("scale", new double[][]{{0.8, 0.8}, {1.2, 1.2}});
    model.component("comp1").geom("geom1").feature("ext3").set("displ", new double[][]{{0, 0}, {0, 0}});
    model.component("comp1").geom("geom1").feature("ext3").set("twist", new int[]{0, 0});
    model.component("comp1").geom("geom1").feature("ext3").selection("input").set("wp2");
    model.component("comp1").geom("geom1").feature("ext3").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").set("keeptool", true);
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("ext3");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").set("uni1(1)");
    model.component("comp1").geom("geom1").create("extract1", "Extract");
    model.component("comp1").geom("geom1").feature("extract1").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("extract1").selection("input")
         .set("par1(1)", 13, 14, 15, 16, 18, 23, 30);
    model.component("comp1").geom("geom1").create("extract2", "Extract");
    model.component("comp1").geom("geom1").feature("extract2").set("inputhandling", "remainder");
    model.component("comp1").geom("geom1").feature("extract2").selection("input").set("extract1(1)", 3, 6, 7);
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("planetype", "vertices");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").selection("vertex1").set("uni1(1)", 40);
    model.component("comp1").geom("geom1").feature("wp3").selection("vertex2").set("uni1(1)", 41);
    model.component("comp1").geom("geom1").feature("wp3").selection("vertex3").set("extract2(2)", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cro1").selection("input")
         .set("uni1", "extract2");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("cm1", "CentroidMeasurement");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cm1")
         .set("parval", new double[]{0.03782438931463053, 0.03299999999999998});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cm1").selection("ent")
         .set("cro1.extract2(2)", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("cm2", "CentroidMeasurement");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cm2")
         .set("parval", new double[]{0.06494279320022003, 0.020999999999999897});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cm2").selection("ent")
         .set("cro1.extract2(2)", 2);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("cm3", "CentroidMeasurement");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cm3")
         .set("parval", new double[]{0.12105875824093652, 0.020999999999999994});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cm3").selection("ent")
         .set("cro1.extract2(2)", 5);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1")
         .set("table", new String[][]{{"geom1.wp3.cm1.xw", "r_filter"}, 
         {"geom1.wp3.cm1.xw*1.07", "r_filter*0.9"}, 
         {"geom1.wp3.cm1.xw*0.94", "r_filter*0.75"}, 
         {"geom1.wp3.cm1.xw*1.05", "geom1.wp3.cm2.yw*1.1"}, 
         {"geom1.wp3.cm2.xw*0.9", "geom1.wp3.cm2.yw*1.2"}, 
         {"geom1.wp3.cm2.xw*1.1", "(geom1.wp3.cm2.yw+geom1.wp3.cm3.yw)/2"}, 
         {"geom1.wp3.cm2.xw*1.3", "(geom1.wp3.cm2.yw+geom1.wp3.cm3.yw)/2"}, 
         {"geom1.wp3.cm3.xw", "geom1.wp3.cm3.yw"}, 
         {"geom1.wp3.cm3.xw*1.4", "geom1.wp3.cm3.yw"}, 
         {"geom1.wp3.cm3.xw*1.42", "r_filter*0.73"}, 
         {"geom1.wp3.cm3.xw*1.37", "r_filter*0.89"}, 
         {"geom1.wp3.cm3.xw*1.4", "r_filter"}});
    model.component("comp1").geom("geom1").create("rev3", "Revolve");
    model.component("comp1").geom("geom1").feature("rev3").set("angtype", "full");
    model.component("comp1").geom("geom1").feature("rev3").set("origfaces", false);
    model.component("comp1").geom("geom1").feature("rev3").set("axis", new int[]{1, 0});
    model.component("comp1").geom("geom1").feature("rev3").selection("input").set("wp3");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("extract2(2)", "rev3");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("planetype", "vertices");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").selection("vertex1").set("uni1(1)", 29);
    model.component("comp1").geom("geom1").feature("wp4").selection("vertex2").set("uni1(1)", 68);
    model.component("comp1").geom("geom1").feature("wp4").selection("vertex3").set("extract2(1)", 8);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cro1").selection("input")
         .set("uni1", "extract2(1)", "int1");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("uni1").selection("input").set("cro1");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pare1").setIndex("param", "1-0.22", 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pare1").selection("edge")
         .set("uni1(1)", 13, 15);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("pare2", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pare2").setIndex("param", "0.55", 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pare2").selection("edge")
         .set("pare1(1)", 15);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("pare3", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pare3").setIndex("param", "0.45", 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pare3").selection("edge")
         .set("pare2(1)", 23);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("cm1", "CentroidMeasurement");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cm1")
         .set("parval", new double[]{-0.003732314250339834, 0.09732036431523336});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cm1").selection("ent").set("pare3(1)", 5);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("cm2", "CentroidMeasurement");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cm2")
         .set("parval", new double[]{-0.0013012734185326997, 0.030099380651714774});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cm2").selection("ent").set("pare3(1)", 8);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("cm3", "CentroidMeasurement");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cm3")
         .set("parval", new double[]{0.014311579593672847, 0.061894137499977506});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cm3").selection("ent").set("pare3(1)", 14);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("cm4", "CentroidMeasurement");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cm4")
         .set("parval", new double[]{0.021709749977873432, 0.052357669491586774});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cm4").selection("ent").set("pare3(1)", 16);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1")
         .set("table", new String[][]{{"geom1.wp4.cm3.xw*1.38", "(geom1.wp4.cm1.yw+geom1.wp4.cm2.yw)/2"}, 
         {"geom1.wp4.cm3.xw*1.75", "geom1.wp4.cm2.yw*1.85"}, 
         {"geom1.wp4.cm4.xw*.9", "geom1.wp4.cm4.yw"}, 
         {"geom1.wp4.cm3.xw*1.81", "(geom1.wp4.cm4.yw+geom1.wp4.cm2.yw)/2"}, 
         {"geom1.wp4.cm3.xw*1.6", "(geom1.wp4.cm4.yw+geom1.wp4.cm2.yw)/2"}, 
         {"geom1.wp4.cm3.xw*0.8", "geom1.wp4.cm2.yw"}, 
         {"-r_filter", "geom1.wp4.cm2.yw"}, 
         {"-r_filter", "geom1.wp4.cm1.yw"}, 
         {"geom1.wp4.cm3.xw*1.38", "geom1.wp4.cm1.yw"}, 
         {"geom1.wp4.cm3.xw", "(geom1.wp4.cm1.yw+geom1.wp4.cm2.yw)*0.53"}});
    model.component("comp1").geom("geom1").create("ext4", "Extrude");
    model.component("comp1").geom("geom1").feature("ext4").set("distance", new double[]{-0.02, 0.05});
    model.component("comp1").geom("geom1").feature("ext4").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext4").set("scale", new double[][]{{1, 1}, {1, 1}});
    model.component("comp1").geom("geom1").feature("ext4").set("displ", new double[][]{{0, 0}, {0, 0}});
    model.component("comp1").geom("geom1").feature("ext4").set("twist", new int[]{0, 0});
    model.component("comp1").geom("geom1").feature("ext4").selection("input").set("wp4");
    model.component("comp1").geom("geom1").create("int2", "Intersection");
    model.component("comp1").geom("geom1").feature("int2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").feature("int2").selection("input").set("ext4", "extract2(1)");
    model.component("comp1").geom("geom1").create("igv1", "IgnoreVertices");
    model.component("comp1").geom("geom1").feature("igv1").selection("input")
         .set("fin(1)", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130);
    model.component("comp1").geom("geom1").nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").label("Water filter");
    model.component("comp1").geom("geom1").nodeGroup("grp1").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("cyl2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("rev2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("ext1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("tor1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("ext2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("rev1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("uni1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("cyl3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("dif1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("sel1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("sel2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("difsel1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("sel3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("sel4");
    model.component("comp1").geom("geom1").nodeGroup().create("grp2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").label("Fracture");
    model.component("comp1").geom("geom1").nodeGroup("grp2").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("wp2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("ext3");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("par1");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("extract1");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("extract2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("wp3");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("rev3");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("int1");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("wp4");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("ext4");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("int2");
    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").run("int2");

    model.title(null);

    model.description("");

    model.label("ceramic_water_filter_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
