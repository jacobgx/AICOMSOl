/*
 * equivalent_properties_of_periodic_microstructures.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:17 by COMSOL 6.3.0.290. */
public class equivalent_properties_of_periodic_microstructures {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.param().set("cell_width", "4[mm]");
    model.param().descr("cell_width", "Cell width");
    model.param().set("cell_depth", "2[mm]");
    model.param().descr("cell_depth", "Cell depth");
    model.param().set("cell_height", "2[mm]");
    model.param().descr("cell_height", "Cell height");
    model.param().set("pA0", "1[bar]");
    model.param().descr("pA0", "Absolute pressure");
    model.param().set("T0", "293.15[K]");
    model.param().descr("T0", "Temperature");
    model.param().set("meshRefine", "5");
    model.param().descr("meshRefine", "Mesh refinement");
    model.param().set("rtol", "1e-3");
    model.param().descr("rtol", "Solver relative tolerance");
    model.param().set("ktol", "5e-3");
    model.param().descr("ktol", "Thermal conductivity tolerance");
    model.param().set("part_sph_R", "0.4[mm]");
    model.param().descr("part_sph_R", "Part, spherical particles, radius");
    model.param().set("part_sph_nx", "1");
    model.param().descr("part_sph_nx", "Part, spherical particles, sphere number, x direction");
    model.param().set("part_sph_ny", "1");
    model.param().descr("part_sph_ny", "Part, spherical particles, sphere number, y direction");
    model.param().set("part_sph_nz", "1");
    model.param().descr("part_sph_nz", "Part, spherical particles, sphere number, z direction");
    model.param().set("part_cyl_R", "0.3[mm]");
    model.param().descr("part_cyl_R", "Part, cylindrical fibers, radius");
    model.param().set("part_cyl_nx", "1");
    model.param().descr("part_cyl_nx", "Part, cylindrical fibers, fiber number, x direction");
    model.param().set("part_cyl_ny", "1");
    model.param().descr("part_cyl_ny", "Part, cylindrical fibers, fiber number, y direction");
    model.param().set("part_plates_th1", "1[mm]");
    model.param().descr("part_plates_th1", "Part, parallel layers, thickness 1");
    model.param().set("part_plates_th2", "1[mm]");
    model.param().descr("part_plates_th2", "Part, parallel layers, thickness 2");
    model.param().set("part_plates_th3", "1[mm]");
    model.param().descr("part_plates_th3", "Part, parallel layers, thickness 3");
    model.param().set("part_plates_th4", "1[mm]");
    model.param().descr("part_plates_th4", "Part, parallel layers, thickness 4");
    model.param().set("part_plates_th5", "1[mm]");
    model.param().descr("part_plates_th5", "Part, parallel layers, thickness 5");
    model.param().set("part_plates_th6", "1[mm]");
    model.param().descr("part_plates_th6", "Part, parallel layers, thickness 6");
    model.param().set("part_plates_th7", "1[mm]");
    model.param().descr("part_plates_th7", "Part, parallel layers, thickness 7");
    model.param().set("part_plates_last", "(cell_width-part_plates_th1-part_plates_th2-part_plates_th3)/1[mm]");
    model.param().descr("part_plates_last", "Part, parallel layers, last thickness");
    model.param().set("part_hon_type", "0");
    model.param().descr("part_hon_type", "Part, honeycomb, type ID");
    model.param().set("part_hon_hex_th", "0.1[mm]");
    model.param().descr("part_hon_hex_th", "Part, honeycomb, hexagonal, thickness");
    model.param().set("part_hon_hex_ex", "sqrt((cell_depth/2)^2+(cell_width/6)^2)*part_hon_hex_th/cell_depth");
    model.param().descr("part_hon_hex_ex", "Part, honeycomb, hexagonal, x-thickness");
    model.param().set("part_hon_hex_dx", "part_hon_hex_ex-part_hon_hex_th*cell_width/(6*cell_depth)");
    model.param().descr("part_hon_hex_dx", "Part, honeycomb, hexagonal, x-offset");
    model.param().set("part_hon_hex_dy", "part_hon_hex_th/2");
    model.param().descr("part_hon_hex_dy", "Part, honeycomb, hexagonal, y-offset");
    model.param().set("part_hon_rec1_th", "0.1[mm]");
    model.param().descr("part_hon_rec1_th", "Part, honeycomb, rectangular-aligned, thickness");
    model.param().set("part_hon_rec2_th", "0.1[mm]");
    model.param().descr("part_hon_rec2_th", "Part, honeycomb, rectangular-staggered, thickness");
    model.param().set("part_hon_cyl_th", "0.1[mm]");
    model.param().descr("part_hon_cyl_th", "Part, honeycomb, cylindrical, thickness");
    model.param()
         .set("part_hon_cyl_R", "min(min((cell_depth-part_hon_cyl_th)/2,(sqrt((cell_width/2)^2+(cell_depth/2)^2)-part_hon_cyl_th)/2),(cell_width-part_hon_cyl_th)/2)/1[mm]");
    model.param().descr("part_hon_cyl_R", "Part, honeycomb, cylindrical, radius");
    model.param().set("part_hon_tri_th", "0.1[mm]");
    model.param().descr("part_hon_tri_th", "Part, honeycomb, triangular, thickness");

    model.geom().create("part1", "Part", 3);
    model.geom("part1").geomRep("comsol");
    model.geom("part1").create("pch1", "ParameterCheck");
    model.geom("part1").feature("pch1").set("condition", "part_plates_last<=0");
    model.geom("part1").feature("pch1")
         .set("message", "The layers are not confined in the unit cell, the thickness of the last layer must be strictly positive.");
    model.geom("part1").run("pch1");
    model.geom("part1").create("blk1", "Block");
    model.geom("part1").feature("blk1").set("size", new String[]{"cell_width", "1", "1"});
    model.geom("part1").feature("blk1").setIndex("size", "cell_depth", 1);
    model.geom("part1").feature("blk1").setIndex("size", "cell_height", 2);
    model.geom("part1").feature("blk1").setIndex("layername", "Layer 1", 0);
    model.geom("part1").feature("blk1").setIndex("layer", "part_plates_th1", 0);
    model.geom("part1").feature("blk1").setIndex("layername", "Layer 2", 1);
    model.geom("part1").feature("blk1").setIndex("layer", "part_plates_th2", 1);
    model.geom("part1").feature("blk1").setIndex("layername", "Layer 3", 2);
    model.geom("part1").feature("blk1").setIndex("layer", "part_plates_th3", 2);
    model.geom("part1").feature("blk1").set("layerleft", true);
    model.geom("part1").feature("blk1").set("layerbottom", false);
    model.geom("part1").run("blk1");
    model.geom().create("part2", "Part", 3);
    model.geom("part2").geomRep("comsol");
    model.geom("part2").create("pch1", "ParameterCheck");
    model.geom("part2").feature("pch1")
         .set("condition", "cell_width/part_cyl_nx-2*part_cyl_R<=0||cell_depth/part_cyl_ny-2*part_cyl_R<=0");
    model.geom("part2").feature("pch1").set("message", "The cylinders overlap.");
    model.geom("part2").run("pch1");
    model.geom("part2").create("pch2", "ParameterCheck");
    model.geom("part2").feature("pch2").set("condition", "part_cyl_R<min(cell_width, cell_depth)/50");
    model.geom("part2").feature("pch2")
         .set("message", "The cylinder radii should be greater than 1/50th of the width and depth of the cell.");
    model.geom("part2").run("pch2");
    model.geom("part2").create("cyl1", "Cylinder");
    model.geom("part2").feature("cyl1").set("r", "part_cyl_R");
    model.geom("part2").feature("cyl1").set("h", "cell_height");
    model.geom("part2").feature("cyl1").set("pos", new String[]{"cell_width/(2*part_cyl_nx)", "0", "0"});
    model.geom("part2").feature("cyl1").setIndex("pos", "cell_depth/(2*part_cyl_ny)", 1);
    model.geom("part2").run("cyl1");
    model.geom("part2").create("arr1", "Array");
    model.geom("part2").feature("arr1").selection("input").set("cyl1");
    model.geom("part2").feature("arr1").set("fullsize", new String[]{"part_cyl_nx", "1", "1"});
    model.geom("part2").feature("arr1").setIndex("fullsize", "part_cyl_ny", 1);
    model.geom("part2").feature("arr1").set("displ", new String[]{"cell_width/part_cyl_nx", "0", "0"});
    model.geom("part2").feature("arr1").setIndex("displ", "cell_depth/part_cyl_ny", 1);
    model.geom("part2").run("arr1");
    model.geom().create("part3", "Part", 3);
    model.geom("part3").geomRep("comsol");
    model.geom("part3").create("pch1", "ParameterCheck");
    model.geom("part3").feature("pch1")
         .set("condition", "cell_width/part_sph_nx-2*part_sph_R<=0||cell_depth/part_sph_ny-2*part_sph_R<=0||cell_height/part_sph_nz-2*part_sph_R<=0");
    model.geom("part3").feature("pch1").set("message", "The spheres overlap.");
    model.geom("part3").run("pch1");
    model.geom("part3").create("pch2", "ParameterCheck");
    model.geom("part3").feature("pch2")
         .set("condition", "part_sph_R<min(min(cell_width, cell_depth), cell_height)/50");
    model.geom("part3").feature("pch2")
         .set("message", "The sphere radii should be greater than 1/50th of the minimum side length of the cell.");
    model.geom("part3").run("pch2");
    model.geom("part3").create("sph1", "Sphere");
    model.geom("part3").feature("sph1").set("r", "part_sph_R");
    model.geom("part3").feature("sph1").set("pos", new String[]{"cell_width/(2*part_sph_nx)", "0", "0"});
    model.geom("part3").feature("sph1").setIndex("pos", "cell_depth/(2*part_sph_ny)", 1);
    model.geom("part3").feature("sph1").setIndex("pos", "cell_height/(2*part_sph_nz)", 2);
    model.geom("part3").run("sph1");
    model.geom("part3").create("arr1", "Array");
    model.geom("part3").feature("arr1").selection("input").set("sph1");
    model.geom("part3").feature("arr1").set("fullsize", new String[]{"part_sph_nx", "1", "1"});
    model.geom("part3").feature("arr1").setIndex("fullsize", "part_sph_ny", 1);
    model.geom("part3").feature("arr1").setIndex("fullsize", "part_sph_nz", 2);
    model.geom("part3").feature("arr1").set("displ", new String[]{"cell_width/part_sph_nx", "0", "0"});
    model.geom("part3").feature("arr1").setIndex("displ", "cell_depth/part_sph_ny", 1);
    model.geom("part3").feature("arr1").setIndex("displ", "cell_height/part_sph_nz", 2);
    model.geom("part3").run("arr1");
    model.geom().create("part4", "Part", 3);
    model.geom("part4").geomRep("comsol");
    model.geom("part4").inputParam().set("type", "part_hon_type");
    model.geom("part4").create("if1", "If");
    model.geom("part4").feature().createAfter("endif1", "EndIf", "if1");
    model.geom("part4").feature("if1").set("condition", "type==0");
    model.geom("part4").create("pch1", "ParameterCheck");
    model.geom("part4").feature("pch1").set("condition", "part_hon_hex_th<min(cell_width, cell_depth)/50");
    model.geom("part4").feature("pch1")
         .set("message", "The thickness should be greater than 1/50th of the minimum side length of the cell.");
    model.geom("part4").run("pch1");
    model.geom("part4").create("wp1", "WorkPlane");
    model.geom("part4").feature("wp1").set("unite", true);
    model.geom("part4").feature("wp1").geom().create("pol1", "Polygon");
    model.geom("part4").feature("wp1").geom().feature("pol1").set("source", "table");
    model.geom("part4").feature("wp1").geom().feature("pol1")
         .setIndex("table", "cell_width*5/6-part_hon_hex_ex", 0, 0);
    model.geom("part4").feature("wp1").geom().feature("pol1").setIndex("table", "cell_depth/2", 0, 1);
    model.geom("part4").feature("wp1").geom().feature("pol1")
         .setIndex("table", "cell_width*4/6-part_hon_hex_dx", 1, 0);
    model.geom("part4").feature("wp1").geom().feature("pol1").setIndex("table", "cell_depth-part_hon_hex_dy", 1, 1);
    model.geom("part4").feature("wp1").geom().feature("pol1")
         .setIndex("table", "cell_width*2/6+part_hon_hex_dx", 2, 0);
    model.geom("part4").feature("wp1").geom().feature("pol1").setIndex("table", "cell_depth-part_hon_hex_dy", 2, 1);
    model.geom("part4").feature("wp1").geom().feature("pol1")
         .setIndex("table", "cell_width/6+part_hon_hex_ex", 3, 0);
    model.geom("part4").feature("wp1").geom().feature("pol1").setIndex("table", "cell_depth/2", 3, 1);
    model.geom("part4").feature("wp1").geom().feature("pol1")
         .setIndex("table", "cell_width*2/6+part_hon_hex_dx", 4, 0);
    model.geom("part4").feature("wp1").geom().feature("pol1").setIndex("table", "part_hon_hex_dy", 4, 1);
    model.geom("part4").feature("wp1").geom().feature("pol1")
         .setIndex("table", "cell_width*4/6-part_hon_hex_dx", 5, 0);
    model.geom("part4").feature("wp1").geom().feature("pol1").setIndex("table", "part_hon_hex_dy", 5, 1);
    model.geom("part4").feature("wp1").geom().run("pol1");
    model.geom("part4").feature("wp1").geom().create("copy1", "Copy");
    model.geom("part4").feature("wp1").geom().feature("copy1").selection("input").set("pol1");
    model.geom("part4").feature("wp1").geom().feature("copy1")
         .set("displx", "-cell_width/2 -cell_width/2 cell_width/2 cell_width/2");
    model.geom("part4").feature("wp1").geom().feature("copy1")
         .set("disply", "-cell_depth/2 cell_depth/2 -cell_depth/2 cell_depth/2");
    model.geom("part4").feature("wp1").geom().run("copy1");
    model.geom("part4").feature("wp1").geom().create("r1", "Rectangle");
    model.geom("part4").feature("wp1").geom().feature("r1").set("size", new String[]{"cell_width", "cell_depth"});
    model.geom("part4").feature("wp1").geom().run("r1");
    model.geom("part4").feature("wp1").geom().create("dif1", "Difference");
    model.geom("part4").feature("wp1").geom().feature("dif1").selection("input").set("r1");
    model.geom("part4").feature("wp1").geom().feature("dif1").selection("input2").set("copy1", "pol1");
    model.geom("part4").feature("wp1").geom().run("dif1");
    model.geom("part4").run("wp1");
    model.geom("part4").feature().create("ext1", "Extrude");
    model.geom("part4").feature("ext1").setIndex("distance", "cell_height", 0);
    model.geom("part4").run("ext1");
    model.geom("part4").create("elseif1", "ElseIf");
    model.geom("part4").feature("elseif1").set("condition", "type==1");
    model.geom("part4").run("elseif1");
    model.geom("part4").create("pch2", "ParameterCheck");
    model.geom("part4").feature("pch2").set("condition", "part_hon_rec1_th<min(cell_width, cell_depth)/50");
    model.geom("part4").feature("pch2")
         .set("message", "The thickness should be greater than 1/50th of the minimum side length of the cell.");
    model.geom("part4").run("pch2");
    model.geom("part4").create("blk1", "Block");
    model.geom("part4").feature("blk1").set("size", new String[]{"cell_width", "1", "1"});
    model.geom("part4").feature("blk1").setIndex("size", "cell_depth", 1);
    model.geom("part4").feature("blk1").setIndex("size", "cell_height", 2);
    model.geom("part4").run("blk1");
    model.geom("part4").create("blk2", "Block");
    model.geom("part4").feature("blk2").set("size", new String[]{"cell_width-part_hon_rec1_th", "1", "1"});
    model.geom("part4").feature("blk2").setIndex("size", "cell_depth-part_hon_rec1_th", 1);
    model.geom("part4").feature("blk2").setIndex("size", "cell_height", 2);
    model.geom("part4").feature("blk2").set("pos", new String[]{"part_hon_rec1_th/2", "0", "0"});
    model.geom("part4").feature("blk2").setIndex("pos", "part_hon_rec1_th/2", 1);
    model.geom("part4").run("blk2");
    model.geom("part4").create("dif1", "Difference");
    model.geom("part4").feature("dif1").selection("input").set("blk1");
    model.geom("part4").feature("dif1").selection("input2").set("blk2");
    model.geom("part4").run("dif1");
    model.geom("part4").create("elseif2", "ElseIf");
    model.geom("part4").feature("elseif2").set("condition", "type==2");
    model.geom("part4").run("elseif2");
    model.geom("part4").create("pch3", "ParameterCheck");
    model.geom("part4").feature("pch3").set("condition", "part_hon_rec2_th<min(cell_width, cell_depth)/50");
    model.geom("part4").feature("pch3")
         .set("message", "The thickness should be greater than 1/50th of the minimum side length of the cell.");
    model.geom("part4").run("pch3");
    model.geom("part4").create("blk3", "Block");
    model.geom("part4").feature("blk3").set("size", new String[]{"cell_width", "1", "1"});
    model.geom("part4").feature("blk3").setIndex("size", "cell_depth", 1);
    model.geom("part4").feature("blk3").setIndex("size", "cell_height", 2);
    model.geom("part4").run("blk3");
    model.geom("part4").create("blk4", "Block");
    model.geom("part4").feature("blk4").set("size", new String[]{"cell_width/4", "1", "1"});
    model.geom("part4").feature("blk4").setIndex("size", "cell_depth-part_hon_rec2_th", 1);
    model.geom("part4").feature("blk4").setIndex("size", "cell_height", 2);
    model.geom("part4").feature("blk4").set("pos", new String[]{"-part_hon_rec2_th/2", "0", "0"});
    model.geom("part4").feature("blk4").setIndex("pos", "part_hon_rec2_th/2", 1);
    model.geom("part4").run("blk4");
    model.geom("part4").create("blk5", "Block");
    model.geom("part4").feature("blk5").set("size", new String[]{"cell_width/2-part_hon_rec2_th", "1", "1"});
    model.geom("part4").feature("blk5").setIndex("size", "cell_depth/2", 1);
    model.geom("part4").feature("blk5").setIndex("size", "cell_height", 2);
    model.geom("part4").feature("blk5").set("pos", new String[]{"cell_width/4+part_hon_rec2_th/2", "0", "0"});
    model.geom("part4").feature("blk5").setIndex("pos", "-part_hon_rec2_th/2", 1);
    model.geom("part4").run("blk5");
    model.geom("part4").feature().duplicate("blk6", "blk5");
    model.geom("part4").feature("blk6").setIndex("pos", "cell_depth/2+part_hon_rec2_th/2", 1);
    model.geom("part4").run("blk6");
    model.geom("part4").feature().duplicate("blk7", "blk4");
    model.geom("part4").feature("blk7").setIndex("pos", "cell_width*3/4+part_hon_rec2_th/2", 0);
    model.geom("part4").run("blk7");
    model.geom("part4").create("dif2", "Difference");
    model.geom("part4").feature("dif2").selection("input").set("blk3");
    model.geom("part4").feature("dif2").selection("input2").set("blk4", "blk5", "blk6", "blk7");
    model.geom("part4").run("dif2");
    model.geom("part4").create("elseif3", "ElseIf");
    model.geom("part4").feature("elseif3").set("condition", "type==3");
    model.geom("part4").run("elseif3");
    model.geom("part4").create("pch4", "ParameterCheck");
    model.geom("part4").feature("pch4").set("condition", "part_hon_cyl_th<min(cell_width, cell_depth)/50");
    model.geom("part4").feature("pch4")
         .set("message", "The thickness should be greater than 1/50th of the minimum side length of the cell.");
    model.geom("part4").run("pch4");
    model.geom("part4").create("cyl1", "Cylinder");
    model.geom("part4").feature("cyl1")
         .set("r", "min(min((cell_depth-part_hon_cyl_th)/2,(sqrt((cell_width/2)^2+(cell_depth/2)^2)-part_hon_cyl_th)/2),(cell_width-part_hon_cyl_th)/2)");
    model.geom("part4").feature("cyl1").set("h", "cell_height");
    model.geom("part4").feature("cyl1").set("pos", new String[]{"cell_width/2", "0", "0"});
    model.geom("part4").feature("cyl1").setIndex("pos", "cell_depth/2", 1);
    model.geom("part4").run("cyl1");
    model.geom("part4").create("copy1", "Copy");
    model.geom("part4").feature("copy1").selection("input").set("cyl1");
    model.geom("part4").feature("copy1").set("displx", "-cell_width/2 -cell_width/2 cell_width/2 cell_width/2");
    model.geom("part4").feature("copy1").set("disply", "-cell_depth/2 cell_depth/2 -cell_depth/2 cell_depth/2");
    model.geom("part4").run("copy1");
    model.geom("part4").create("blk8", "Block");
    model.geom("part4").feature("blk8").set("size", new String[]{"cell_width", "1", "1"});
    model.geom("part4").feature("blk8").setIndex("size", "cell_depth", 1);
    model.geom("part4").feature("blk8").setIndex("size", "cell_height", 2);
    model.geom("part4").run("blk8");
    model.geom("part4").create("dif3", "Difference");
    model.geom("part4").feature("dif3").selection("input").set("blk8");
    model.geom("part4").feature("dif3").selection("input2").set("copy1", "cyl1");
    model.geom("part4").run("dif3");
    model.geom("part4").create("elseif4", "ElseIf");
    model.geom("part4").feature("elseif4").set("condition", "type==4");
    model.geom("part4").run("elseif4");
    model.geom("part4").create("pch5", "ParameterCheck");
    model.geom("part4").feature("pch5").set("condition", "part_hon_tri_th<min(cell_width, cell_depth)/50");
    model.geom("part4").feature("pch5")
         .set("message", "The thickness should be greater than 1/50th of the minimum side length of the cell.");
    model.geom("part4").run("pch5");
    model.geom("part4").create("blk9", "Block");
    model.geom("part4").feature("blk9").set("size", new String[]{"part_hon_tri_th", "1", "1"});
    model.geom("part4").feature("blk9").setIndex("size", "cell_depth", 1);
    model.geom("part4").feature("blk9").setIndex("size", "cell_height", 2);
    model.geom("part4").feature("blk9").set("pos", new String[]{"-part_hon_tri_th/2", "0", "0"});
    model.geom("part4").run("blk9");
    model.geom("part4").feature().duplicate("blk10", "blk9");
    model.geom("part4").feature("blk10").setIndex("size", "sqrt(cell_depth^2+cell_width^2)", 1);
    model.geom("part4").run("blk10");
    model.geom("part4").create("rot1", "Rotate");
    model.geom("part4").feature("rot1").selection("input").set("blk10");
    model.geom("part4").feature("rot1").set("rot", "-atan(cell_width/cell_depth)[rad]");
    model.geom("part4").run("rot1");
    model.geom("part4").create("mir1", "Mirror");
    model.geom("part4").feature("mir1").selection("input").set("rot1");
    model.geom("part4").feature("mir1").set("keep", true);
    model.geom("part4").feature("mir1").set("pos", new String[]{"0", "cell_depth/2", "0"});
    model.geom("part4").feature("mir1").set("axis", new int[]{0, 1, 0});
    model.geom("part4").run("mir1");
    model.geom("part4").create("copy2", "Copy");
    model.geom("part4").feature("copy2").selection("input").set("blk9");
    model.geom("part4").feature("copy2").set("displx", "cell_width/2 cell_width");
    model.geom("part4").run("copy2");
    model.geom("part4").create("blk11", "Block");
    model.geom("part4").feature("blk11").set("size", new String[]{"cell_width", "1", "1"});
    model.geom("part4").feature("blk11").setIndex("size", "cell_depth", 1);
    model.geom("part4").feature("blk11").setIndex("size", "cell_height", 2);
    model.geom("part4").run("blk11");
    model.geom("part4").create("dif4", "Difference");
    model.geom("part4").feature("dif4").selection("input").set("blk11");
    model.geom("part4").feature("dif4").selection("input2").set("blk9", "copy2", "mir1", "rot1");
    model.geom("part4").run("dif4");
    model.geom("part4").feature().duplicate("blk12", "blk11");
    model.geom("part4").run("blk12");
    model.geom("part4").create("dif5", "Difference");
    model.geom("part4").feature("dif5").selection("input").set("blk12");
    model.geom("part4").feature("dif5").selection("input2").set("dif4");
    model.geom("part4").run("dif5");
    model.geom("part4").run("endif1");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part4");
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"cell_width", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "cell_depth", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "cell_height", 2);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1")
         .label("Explicit Selection: Periodic Boundaries, x Direction");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("blk1", 2, 5);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2")
         .label("Explicit Selection: Periodic Boundaries, y Direction");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("blk1", 3, 6);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3")
         .label("Explicit Selection: Periodic Boundaries, z Direction");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("blk1", 1, 4);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("Explicit Selection: Origin Point");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(0);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("blk1", 1);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("Explicit Selection: Cell Components");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init();
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("pi1");
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").label("Explicit Selection: Boundaries to Hide");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").set("blk1", 2, 3, 4);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("Difference Selection: Hidden Boundaries");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"sel6"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"sel5"});
    model.component("comp1").geom("geom1").run("difsel1");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").label("Average: All Domains");
    model.component("comp1").cpl("aveop1").selection().all();
    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").label("Integration: Periodic Boundaries, x Direction");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("geom1_sel1");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").label("Integration: Periodic Boundaries, y Direction");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop2").selection().named("geom1_sel2");
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").label("Integration: Periodic Boundaries, z Direction");
    model.component("comp1").cpl("intop3").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop3").selection().named("geom1_sel3");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("Selection: Domain Material");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("Selection: All Domains");
    model.component("comp1").selection("sel2").all();

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(2);
    model.component("comp1").view("view1").hideObjects("hide1").named("difsel1");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").label("Air");
    model.component("comp1").material("mat1").set("family", "air");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat1").materialType("nonSolid");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Acrylic plastic");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2")
         .set("customspecular", new double[]{0.9803921568627451, 0.9803921568627451, 0.9803921568627451});
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.39215686274509803, 0.7843137254901961, 0.39215686274509803});
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.39215686274509803, 0.7843137254901961, 0.39215686274509803});
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("lighting", "phong");
    model.component("comp1").material("mat2").set("shininess", 1000);
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.0e-5[1/K]", "0", "0", "0", "7.0e-5[1/K]", "0", "0", "0", "7.0e-5[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "1470[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "1190[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.18[W/(m*K)]", "0", "0", "0", "0.18[W/(m*K)]", "0", "0", "0", "0.18[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "3.2[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat3").label("Aluminum");
    model.component("comp1").material("mat3").set("family", "aluminum");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").label("Brick");
    model.component("comp1").material("mat4").set("family", "brick");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"6e-6[1/K]", "0", "0", "0", "6e-6[1/K]", "0", "0", "0", "6e-6[1/K]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "2000[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.5[W/(m*K)]", "0", "0", "0", "0.5[W/(m*K)]", "0", "0", "0", "0.5[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", "17[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", "0.2");
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").label("Cast iron");
    model.component("comp1").material("mat5").set("family", "custom");
    model.component("comp1").material("mat5")
         .set("customspecular", new double[]{0.9803921568627451, 0.9803921568627451, 0.9803921568627451});
    model.component("comp1").material("mat5").set("diffuse", "custom");
    model.component("comp1").material("mat5")
         .set("customdiffuse", new double[]{0.39215686274509803, 0.39215686274509803, 0.39215686274509803});
    model.component("comp1").material("mat5").set("ambient", "custom");
    model.component("comp1").material("mat5")
         .set("customambient", new double[]{0.39215686274509803, 0.39215686274509803, 0.39215686274509803});
    model.component("comp1").material("mat5").set("noise", true);
    model.component("comp1").material("mat5").set("noisescale", 0.14);
    model.component("comp1").material("mat5").set("noisefreq", 2.1);
    model.component("comp1").material("mat5").set("fresnel", 0.1);
    model.component("comp1").material("mat5").set("roughness", 0.1);
    model.component("comp1").material("mat5").set("diffusewrap", 0);
    model.component("comp1").material("mat5").set("reflectance", 0);
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"11e-6[1/K]", "0", "0", "0", "11e-6[1/K]", "0", "0", "0", "11e-6[1/K]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "7000[kg/m^3]");
    model.component("comp1").material("mat5").propertyGroup("def").set("heatcapacity", "420[J/(kg*K)]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"50[W/(m*K)]", "0", "0", "0", "50[W/(m*K)]", "0", "0", "0", "50[W/(m*K)]"});
    model.component("comp1").material("mat5").propertyGroup("Enu").set("E", "140[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("nu", "0.25");
    model.component("comp1").material().create("mat6", "Common");
    model.component("comp1").material("mat6").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat6").label("Concrete");
    model.component("comp1").material("mat6").set("family", "concrete");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"10e-6[1/K]", "0", "0", "0", "10e-6[1/K]", "0", "0", "0", "10e-6[1/K]"});
    model.component("comp1").material("mat6").propertyGroup("def").set("density", "2300[kg/m^3]");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.8[W/(m*K)]", "0", "0", "0", "1.8[W/(m*K)]", "0", "0", "0", "1.8[W/(m*K)]"});
    model.component("comp1").material("mat6").propertyGroup("def").set("heatcapacity", "880[J/(kg*K)]");
    model.component("comp1").material("mat6").propertyGroup("Enu").set("E", "25[GPa]");
    model.component("comp1").material("mat6").propertyGroup("Enu").set("nu", "0.20");
    model.component("comp1").material().create("mat7", "Common");
    model.component("comp1").material("mat7").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat7").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat7").label("Copper");
    model.component("comp1").material("mat7").set("family", "copper");
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat7").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat7").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat7").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat7").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat7").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat7").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat7").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat7").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material().create("mat8", "Common");
    model.component("comp1").material("mat8").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat8").label("Glass (quartz)");
    model.component("comp1").material("mat8").set("family", "custom");
    model.component("comp1").material("mat8").set("diffuse", "custom");
    model.component("comp1").material("mat8").set("ambient", "custom");
    model.component("comp1").material("mat8").set("noise", true);
    model.component("comp1").material("mat8").set("fresnel", 0.99);
    model.component("comp1").material("mat8").set("roughness", 0.02);
    model.component("comp1").material("mat8").set("diffusewrap", 0);
    model.component("comp1").material("mat8").set("reflectance", 0);
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]", "0", "0", "0", "1e-14[S/m]", "0", "0", "0", "1e-14[S/m]"});
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.2", "0", "0", "0", "4.2", "0", "0", "0", "4.2"});
    model.component("comp1").material("mat8").propertyGroup("def").set("density", "2210[kg/m^3]");
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]"});
    model.component("comp1").material("mat8").propertyGroup("def").set("heatcapacity", "730[J/(kg*K)]");
    model.component("comp1").material("mat8").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1.5", "0", "0", "0", "1.5", "0", "0", "0", "1.5"});
    model.component("comp1").material().create("mat9", "Common");
    model.component("comp1").material("mat9").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat9").label("Iron");
    model.component("comp1").material("mat9").set("family", "iron");
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("relpermeability", new String[]{"4000", "0", "0", "0", "4000", "0", "0", "0", "4000"});
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]"});
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.2e-6[1/K]", "0", "0", "0", "12.2e-6[1/K]", "0", "0", "0", "12.2e-6[1/K]"});
    model.component("comp1").material("mat9").propertyGroup("def").set("heatcapacity", "440[J/(kg*K)]");
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat9").propertyGroup("def").set("density", "7870[kg/m^3]");
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("thermalconductivity", new String[]{"76.2[W/(m*K)]", "0", "0", "0", "76.2[W/(m*K)]", "0", "0", "0", "76.2[W/(m*K)]"});
    model.component("comp1").material("mat9").propertyGroup("Enu").set("E", "200[GPa]");
    model.component("comp1").material("mat9").propertyGroup("Enu").set("nu", "0.29");
    model.component("comp1").material().create("mat10", "Common");
    model.component("comp1").material("mat10").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat10").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat10").label("Silica glass");
    model.component("comp1").material("mat10").set("family", "custom");
    model.component("comp1").material("mat10").set("diffuse", "custom");
    model.component("comp1").material("mat10").set("ambient", "custom");
    model.component("comp1").material("mat10").set("noise", true);
    model.component("comp1").material("mat10").set("fresnel", 0.99);
    model.component("comp1").material("mat10").set("roughness", 0.02);
    model.component("comp1").material("mat10").set("diffusewrap", 0);
    model.component("comp1").material("mat10").set("reflectance", 0);
    model.component("comp1").material("mat10").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat10").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]", "0", "0", "0", "1e-14[S/m]", "0", "0", "0", "1e-14[S/m]"});
    model.component("comp1").material("mat10").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]"});
    model.component("comp1").material("mat10").propertyGroup("def").set("heatcapacity", "703[J/(kg*K)]");
    model.component("comp1").material("mat10").propertyGroup("def")
         .set("relpermittivity", new String[]{"3.75", "0", "0", "0", "3.75", "0", "0", "0", "3.75"});
    model.component("comp1").material("mat10").propertyGroup("def").set("density", "2203[kg/m^3]");
    model.component("comp1").material("mat10").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]"});
    model.component("comp1").material("mat10").propertyGroup("Enu").set("E", "73.1[GPa]");
    model.component("comp1").material("mat10").propertyGroup("Enu").set("nu", "0.17");
    model.component("comp1").material("mat10").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1.45", "0", "0", "0", "1.45", "0", "0", "0", "1.45"});
    model.component("comp1").material().create("mat11", "Common");
    model.component("comp1").material("mat11").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat11").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat11").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat11").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat11").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat11").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat11").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat11").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat11").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat11").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat11").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat11").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat11").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat11").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat11").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat11").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat11").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat11").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat11").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat11").label("Structural steel");
    model.component("comp1").material("mat11").set("family", "custom");
    model.component("comp1").material("mat11")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat11").set("diffuse", "custom");
    model.component("comp1").material("mat11")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat11").set("ambient", "custom");
    model.component("comp1").material("mat11")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat11").set("noise", true);
    model.component("comp1").material("mat11").set("fresnel", 0.9);
    model.component("comp1").material("mat11").set("roughness", 0.3);
    model.component("comp1").material("mat11").set("diffusewrap", 0);
    model.component("comp1").material("mat11").set("reflectance", 0);
    model.component("comp1").material("mat11").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat11").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat11").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat11").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat11").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat11").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat11").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat11").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat11").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat11").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat11").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat11").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat11").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat11").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat11").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat11").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat11").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat11").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat11").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat11").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat11").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat11").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat11").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat11").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat11").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat11").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat11").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat11").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat11").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat11").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat11").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat11").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat11").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat11").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat11").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat11").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat11").propertyGroup("ElastoplasticModel")
         .addInput("effectiveplasticstrain");
    model.component("comp1").material("mat11").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat11").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat11").propertyGroup("Ludwik").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat11").propertyGroup("Ludwik").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat11").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat11").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat11").propertyGroup("Ludwik").addInput("temperature");

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp1").material("mat11").propertyGroup("JohnsonCook").label("Johnson-Cook");
    model.component("comp1").material("mat11").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat11").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat11").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat11").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat11").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat11").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat11").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat11").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat11").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat11").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat11").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat11").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat11").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat11").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat11").propertyGroup("HockettSherby").label("Hockett-Sherby");
    model.component("comp1").material("mat11").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat11").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat11").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat11").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat11").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat11").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat11").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat11").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat11").propertyGroup("ArmstrongFrederick").label("Armstrong-Frederick");
    model.component("comp1").material("mat11").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat11").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat11").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat11").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat11").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat11").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat11").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat11").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat11").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat11").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat11").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat11").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat11").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat11").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat11").propertyGroup("ChabocheViscoplasticity")
         .set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat11").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.component("comp1").material().create("mat12", "Common");
    model.component("comp1").material("mat12").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat12").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat12").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat12").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat12").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat12").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat12").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat12").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat12").label("Water, liquid");
    model.component("comp1").material("mat12").set("family", "water");
    model.component("comp1").material("mat12").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat12").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat12").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat12").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat12").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat12").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat12").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat12").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat12").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat12").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat12").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat12").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat12").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat12").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat12").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat12").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat12").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat12").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.component("comp1").material("mat12").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat12").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat12").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat12").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat12").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat12").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat12").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat12").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat12").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat12").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat12").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat12").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat12").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat12").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat12").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat12").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat12").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat12").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat12").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat12").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat12").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat12").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat12").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat12").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat12").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat12").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat12").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat12").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat12").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat12").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat12").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat12").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat12").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat12").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat12").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat12").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").selection().set();
    model.component("comp1").material("mat3").selection().set(2);
    model.component("comp1").material("mat3").selection().all();

    model.component("comp1").physics("ht").feature("solid1").set("minput_pressure", "pA0");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("cp1", "CellPeriodicity", 3);
    model.component("comp1").physics("ht").feature("cp1").create("bp1", "BoundaryPair", 2);
    model.component("comp1").physics("ht").feature("cp1").feature("bp1").selection().named("geom1_sel1");
    model.component("comp1").physics("ht").feature("cp1").create("bp2", "BoundaryPair", 2);
    model.component("comp1").physics("ht").feature("cp1").feature("bp2").selection().named("geom1_sel2");
    model.component("comp1").physics("ht").feature("cp1").create("bp3", "BoundaryPair", 2);
    model.component("comp1").physics("ht").feature("cp1").feature("bp3").selection().named("geom1_sel3");
    model.component("comp1").physics("ht").feature("cp1").feature("bp1")
         .set("constraintOptions", "nitscheConstraints");
    model.component("comp1").physics("ht").feature("cp1").feature("bp2")
         .set("constraintOptions", "nitscheConstraints");
    model.component("comp1").physics("ht").feature("cp1").feature("bp3")
         .set("constraintOptions", "nitscheConstraints");

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").geom("geom2").create("imp1", "Import");
    model.component("comp2").geom("geom2").feature("imp1").set("type", "sequence");
    model.component("comp2").geom("geom2").feature("imp1").set("sequence", "part4");
    model.component("comp2").geom("geom2").run("imp1");
    model.component("comp2").geom("geom2").create("sel1", "ExplicitSelection");
    model.component("comp2").geom("geom2").feature("sel1").label("Explicit Selection: Part");
    model.component("comp2").geom("geom2").feature("sel1").selection("selection").init();
    model.component("comp2").geom("geom2").feature("sel1").selection("selection").set("imp1");
    model.component("comp2").geom("geom2").run("sel1");
    model.component("comp2").geom("geom2").create("arr1", "Array");
    model.component("comp2").geom("geom2").feature("arr1").selection("input").set("imp1");
    model.component("comp2").geom("geom2").feature("arr1").set("fullsize", new int[]{3, 3, 3});
    model.component("comp2").geom("geom2").feature("arr1").set("displ", new String[]{"cell_width", "0", "0"});
    model.component("comp2").geom("geom2").feature("arr1").setIndex("displ", "cell_depth", 1);
    model.component("comp2").geom("geom2").feature("arr1").setIndex("displ", "cell_height", 2);
    model.component("comp2").geom("geom2").run("arr1");
    model.component("comp2").geom("geom2").create("blk1", "Block");
    model.component("comp2").geom("geom2").feature("blk1").set("size", new String[]{"3*cell_width", "1", "1"});
    model.component("comp2").geom("geom2").feature("blk1").setIndex("size", "3*cell_depth", 1);
    model.component("comp2").geom("geom2").feature("blk1").setIndex("size", "3*cell_height", 2);
    model.component("comp2").geom("geom2").run("blk1");
    model.component("comp2").geom("geom2").create("sel2", "ExplicitSelection");
    model.component("comp2").geom("geom2").feature("sel2").label("Explicit Selection: Block Boundaries to Hide");
    model.component("comp2").geom("geom2").feature("sel2").selection("selection").init(2);
    model.component("comp2").geom("geom2").feature("sel2").selection("selection").set("blk1", 2, 3, 4);
    model.component("comp2").geom("geom2").run("fin");
    model.component("comp2").geom("geom2").create("difsel1", "DifferenceSelection");
    model.component("comp2").geom("geom2").feature("difsel1").label("Difference Selection: Hidden Boundaries");
    model.component("comp2").geom("geom2").feature("difsel1").set("entitydim", 2);
    model.component("comp2").geom("geom2").feature("difsel1").set("add", new String[]{"sel2"});
    model.component("comp2").geom("geom2").feature("difsel1").set("subtract", new String[]{"sel1"});
    model.component("comp2").geom("geom2").run("difsel1");

    model.component("comp2").view("view7").hideObjects().create("hide1");
    model.component("comp2").view("view7").hideObjects("hide1").init(2);
    model.component("comp2").view("view7").hideObjects("hide1").named("difsel1");

    model.component("comp2").mesh().remove("mesh2");

    model.component("comp1").physics("ht").feature("cp1").runCommand("createLoadGroupsandStudy");

    model.study("htcp1std").createAutoSequences("all");

    model.sol("htcp1sol").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("Temperature (ht)");
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result().evaluationGroup().create("htcp1eg", "EvaluationGroup");
    model.result().evaluationGroup("htcp1eg").label("Effective Material Properties (htcp1)");
    model.result().evaluationGroup("htcp1eg").set("data", "dset1");
    model.result().evaluationGroup("htcp1eg").create("htcp1gmev", "EvalGlobalMatrix");
    model.result().evaluationGroup("htcp1eg").create("htcp1gev", "EvalGlobal");
    model.result().evaluationGroup("htcp1eg").set("includeparameters", "off");
    model.result().evaluationGroup("htcp1eg").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("htcp1eg").feature("htcp1gmev").set("expr", "ht.cp1.k_eff");
    model.result().evaluationGroup("htcp1eg").feature("htcp1gmev").set("descr", "Effective thermal conductivity");
    model.result().evaluationGroup("htcp1eg").feature("htcp1gmev").set("dataseries", "none");
    model.result().evaluationGroup("htcp1eg").feature("htcp1gmev").set("outerdataseries", "none");
    model.result().evaluationGroup("htcp1eg").feature("htcp1gev").setIndex("expr", "ht.cp1.rho_eff", 0);
    model.result().evaluationGroup("htcp1eg").feature("htcp1gev").setIndex("expr", "ht.cp1.Cp_eff", 1);
    model.result().evaluationGroup("htcp1eg").feature("htcp1gev").set("normalization", "none");
    model.result().evaluationGroup("htcp1eg").feature("htcp1gev").set("dataseries", "none");
    model.result().evaluationGroup("htcp1eg").run();
    model.result("pg1").run();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").label("Effective density");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").label("Effective Heat Capacity");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("Effective density");
    model.result().numerical("gev1").setIndex("expr", "ht.cp1.rho_eff", 0);
    model.result().numerical("gev1").setIndex("descr", "Effective density", 0);
    model.result().numerical("gev1").set("dataseries", "average");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("Effective Cp");
    model.result().numerical("gev2").setIndex("expr", "ht.cp1.Cp_eff", 0);
    model.result().numerical("gev2").setIndex("descr", "Effective heat capacity at constant pressure", 0);
    model.result().numerical("gev2").set("dataseries", "average");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").label("Effective Thermal Conductivity xx");
    model.result().numerical("gev3").setIndex("expr", "round(ht.cp1.k_effXX/ktol)*ktol", 0);
    model.result().numerical("gev3").setIndex("descr", "Effective Thermal Conductivity xx", 0);
    model.result().numerical("gev3").setIndex("looplevelinput", "last", 0);
    model.result().numerical().create("gev4", "EvalGlobal");
    model.result().numerical("gev4").label("Effective Thermal Conductivity yy");
    model.result().numerical("gev4").setIndex("expr", "round(ht.cp1.k_effYY/ktol)*ktol", 0);
    model.result().numerical("gev4").setIndex("descr", "Effective Thermal Conductivity yy", 0);
    model.result().numerical("gev4").setIndex("looplevelinput", "last", 0);
    model.result().numerical().create("gev5", "EvalGlobal");
    model.result().numerical("gev5").label("Effective Thermal Conductivity zz");
    model.result().numerical("gev5").setIndex("expr", "round(ht.cp1.k_effZZ/ktol)*ktol", 0);
    model.result().numerical("gev5").setIndex("descr", "Effective Thermal Conductivity zz", 0);
    model.result().numerical("gev5").setIndex("looplevelinput", "last", 0);
    model.result().numerical().create("gev6", "EvalGlobal");
    model.result().numerical("gev6").label("Effective Thermal Conductivity xy");
    model.result().numerical("gev6").setIndex("expr", "round(ht.cp1.k_effXY/ktol)*ktol", 0);
    model.result().numerical("gev6").setIndex("descr", "Effective Thermal Conductivity xy", 0);
    model.result().numerical("gev6").setIndex("looplevelinput", "last", 0);
    model.result().numerical().create("gev7", "EvalGlobal");
    model.result().numerical("gev7").label("Effective Thermal Conductivity xz");
    model.result().numerical("gev7").setIndex("expr", "round(ht.cp1.k_effXZ/ktol)*ktol", 0);
    model.result().numerical("gev7").setIndex("descr", "Effective Thermal Conductivity xz", 0);
    model.result().numerical("gev7").setIndex("looplevelinput", "last", 0);
    model.result().numerical().create("gev8", "EvalGlobal");
    model.result().numerical("gev8").label("Effective Thermal Conductivity yx");
    model.result().numerical("gev8").setIndex("expr", "round(ht.cp1.k_effYX/ktol)*ktol", 0);
    model.result().numerical("gev8").setIndex("descr", "Effective Thermal Conductivity yx", 0);
    model.result().numerical("gev8").setIndex("looplevelinput", "last", 0);
    model.result().numerical().create("gev9", "EvalGlobal");
    model.result().numerical("gev9").label("Effective Thermal Conductivity yz");
    model.result().numerical("gev9").setIndex("expr", "round(ht.cp1.k_effYZ/ktol)*ktol", 0);
    model.result().numerical("gev9").setIndex("descr", "Effective Thermal Conductivity yz", 0);
    model.result().numerical("gev9").setIndex("looplevelinput", "last", 0);
    model.result().numerical().create("gev10", "EvalGlobal");
    model.result().numerical("gev10").label("Effective Thermal Conductivity zx");
    model.result().numerical("gev10").setIndex("expr", "round(ht.cp1.k_effZX/ktol)*ktol", 0);
    model.result().numerical("gev10").setIndex("descr", "Effective Thermal Conductivity zx", 0);
    model.result().numerical("gev10").setIndex("looplevelinput", "last", 0);
    model.result().numerical().create("gev11", "EvalGlobal");
    model.result().numerical("gev11").label("Effective Thermal Conductivity zy");
    model.result().numerical("gev11").setIndex("expr", "round(ht.cp1.k_effZY/ktol)*ktol", 0);
    model.result().numerical("gev11").setIndex("descr", "Effective Thermal Conductivity zy", 0);
    model.result().numerical("gev11").setIndex("looplevelinput", "last", 0);
    model.result("pg1").run();

    model.title("\u5468\u671f\u6027\u5fae\u7ed3\u6784\u7684\u7b49\u6548\u5c5e\u6027");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u5c06\u57fa\u672c\u5355\u5143\u4e2d\u7684\u5468\u671f\u6027\u7ed3\u6784\u53ef\u89c6\u5316\n\u2022 \u91cd\u7f6e\u90e8\u5206\u6216\u5168\u90e8\u8f93\u5165\u53c2\u6570\n\u2022 \u5c06\u751f\u6210\u7684\u6750\u6599\u5c5e\u6027\u5bfc\u51fa\u4e3a MPH \u6216 XML \u6587\u4ef6\uff0c\u5b83\u4eec\u53ef\u4ee5\u5bfc\u5165 COMSOL Multiphysics \u4f1a\u8bdd\u4e2d\n\n\u6211\u4eec\u7ecf\u5e38\u53ef\u4ee5\u5728\u78b3\u7ea4\u7ef4\u548c\u8702\u7a9d\u7ed3\u6784\u7b49\u590d\u5408\u6750\u6599\u4e2d\u53d1\u73b0\u5468\u671f\u6027\u5fae\u7ed3\u6784\uff0c\u5b83\u4eec\u53ef\u4ee5\u7528\u6cbf\u4e09\u4e2a\u4f20\u64ad\u65b9\u5411\u91cd\u590d\u7684\u57fa\u672c\u5355\u5143\u6765\u8868\u793a\u3002\n\n\u4e3a\u4e86\u964d\u4f4e\u8ba1\u7b97\u6210\u672c\uff0c\u4eff\u771f\u53ef\u4ee5\u4f7f\u7528\u5177\u6709\u7b49\u6548\u5c5e\u6027\u7684\u5747\u8d28\u57df\u6765\u4ee3\u66ff\u590d\u5408\u6750\u6599\u7684\u6240\u6709\u5fae\u89c2\u7ec6\u8282\u3002\u6b64 App \u8ba1\u7b97\u51e0\u4f55\u6784\u578b\u7684\u7b49\u6548\u5c5e\u6027\uff0c\u4ee5\u53ca\u91c7\u7528\u8fd9\u4e9b\u590d\u5408\u6750\u6599\u7684\u5b8f\u89c2\u6a21\u578b\u8981\u4f7f\u7528\u7684\u57fa\u672c\u5355\u5143\u7684\u6750\u6599\u5c5e\u6027\u3002\n\n\u672c\u4f8b\u7ed9\u51fa\u4e5d\u79cd\u4e0d\u540c\u7684\u5fae\u7ed3\u6784\uff0c\u5177\u6709\u7528\u6237\u53ef\u4fee\u6539\u7684\u5c3a\u5bf8\u7279\u6027\uff1b\u6b64\u5916\uff0c\u8fd8\u63d0\u4f9b\u5341\u4e09\u79cd\u9884\u5b9a\u4e49\u7684\u6750\u6599\u3002\u8be5 App \u8ba1\u7b97\u590d\u5408\u6750\u6599\u7684\u7b49\u6548\u5bc6\u5ea6\u3001\u70ed\u5bb9\u548c\u5bfc\u70ed\u7cfb\u6570\u6216\u6269\u6563\u7cfb\u6570\u3002");

    model.label("equivalent_properties_of_periodic_microstructures.mph");

    model.result("pg1").run();
    model.result().report().create("rpt1", "Report");

    model.setExpectedComputationTime("4 \u79d2");

    model.result().report("rpt1").set("templatesource", "brief");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").set("filename", "user:///equivalent_properties_of_periodic_microstructures.docx");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").set("titleimage", "none");
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature("toc1").label("\u76ee\u5f55");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u8f6f\u4ef6\u4fe1\u606f");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").label("\u5173\u4e8e\u8f6f\u4ef6");
    model.result().report("rpt1").feature("sec1").feature().create("std1", "Study");
    model.result().report("rpt1").feature("sec1").feature("std1").label("\u7814\u7a76");
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("App \u53c2\u6570");
    model.result().report("rpt1").feature("sec2").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec1").label("\u53c2\u6570");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1").label("\u53c2\u6570");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 22, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 25, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 26, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 27, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 31, 1);
    model.result().report("rpt1").feature("sec2").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec2").label("\u6750\u6599");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec1").feature()
         .create("mat1", "Material");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec1").feature("mat1")
         .set("includeimage", false);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec1").feature("mat1")
         .set("includeselection", false);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().duplicate("sec2", "sec1");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec2").feature("mat1")
         .set("noderef", "mat2");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().duplicate("sec3", "sec2");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec3").feature("mat1")
         .set("noderef", "mat3");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().duplicate("sec4", "sec3");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec4").feature("mat1")
         .set("noderef", "mat4");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().duplicate("sec5", "sec4");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec5").feature("mat1")
         .set("noderef", "mat5");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().duplicate("sec6", "sec5");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec6").feature("mat1")
         .set("noderef", "mat6");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().duplicate("sec7", "sec6");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec7").feature("mat1")
         .set("noderef", "mat7");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().duplicate("sec8", "sec7");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec8").feature("mat1")
         .set("noderef", "mat8");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().duplicate("sec9", "sec8");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec9").feature("mat1")
         .set("noderef", "mat9");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().duplicate("sec10", "sec9");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec10").feature("mat1")
         .set("noderef", "mat10");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().duplicate("sec11", "sec10");

    return model;
  }

  public static Model run4(Model model) {
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec11").feature("mat1")
         .set("noderef", "mat11");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().duplicate("sec12", "sec11");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec12").feature("mat1")
         .set("noderef", "mat12");
    model.result().report("rpt1").feature("sec2").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec3").label("\u6c42\u89e3\u5668\u5bb9\u5dee");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().create("field1", "DoubleDataField");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("field1")
         .set("include", new String[]{"off", "off", "off", "off", "off", "off", "off", "off", "off", "off", 
         "off", "off"});
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("field1").setIndex("include", true, 11);
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("\u7b49\u6548\u70ed\u5c5e\u6027");
    model.result().report("rpt1").feature("sec3").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("mtbl2", "Table");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("mtbl2").set("noderef", "tbl2");
    model.result().report("rpt1").feature("sec3").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature()
         .create("field1", "DoubleMatrixDataField");

    model.title("\u5468\u671f\u6027\u5fae\u7ed3\u6784\u7684\u7b49\u6548\u5c5e\u6027");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u5c06\u57fa\u672c\u5355\u5143\u4e2d\u7684\u5468\u671f\u6027\u7ed3\u6784\u53ef\u89c6\u5316\n\u2022 \u91cd\u7f6e\u90e8\u5206\u6216\u5168\u90e8\u8f93\u5165\u53c2\u6570\n\u2022 \u5c06\u751f\u6210\u7684\u6750\u6599\u5c5e\u6027\u5bfc\u51fa\u4e3a MPH \u6216 XML \u6587\u4ef6\uff0c\u5b83\u4eec\u53ef\u4ee5\u5bfc\u5165 COMSOL Multiphysics \u4f1a\u8bdd\u4e2d\n\n\u6211\u4eec\u7ecf\u5e38\u53ef\u4ee5\u5728\u78b3\u7ea4\u7ef4\u548c\u8702\u7a9d\u7ed3\u6784\u7b49\u590d\u5408\u6750\u6599\u4e2d\u53d1\u73b0\u5468\u671f\u6027\u5fae\u7ed3\u6784\uff0c\u5b83\u4eec\u53ef\u4ee5\u7528\u6cbf\u4e09\u4e2a\u4f20\u64ad\u65b9\u5411\u91cd\u590d\u7684\u57fa\u672c\u5355\u5143\u6765\u8868\u793a\u3002\n\n\u4e3a\u4e86\u964d\u4f4e\u8ba1\u7b97\u6210\u672c\uff0c\u4eff\u771f\u53ef\u4ee5\u4f7f\u7528\u5177\u6709\u7b49\u6548\u5c5e\u6027\u7684\u5747\u8d28\u57df\u6765\u4ee3\u66ff\u590d\u5408\u6750\u6599\u7684\u6240\u6709\u5fae\u89c2\u7ec6\u8282\u3002\u6b64 App \u8ba1\u7b97\u51e0\u4f55\u6784\u578b\u7684\u7b49\u6548\u5c5e\u6027\uff0c\u4ee5\u53ca\u91c7\u7528\u8fd9\u4e9b\u590d\u5408\u6750\u6599\u7684\u5b8f\u89c2\u6a21\u578b\u8981\u4f7f\u7528\u7684\u57fa\u672c\u5355\u5143\u7684\u6750\u6599\u5c5e\u6027\u3002\n\n\u672c\u4f8b\u7ed9\u51fa\u4e5d\u79cd\u4e0d\u540c\u7684\u5fae\u7ed3\u6784\uff0c\u5177\u6709\u7528\u6237\u53ef\u4fee\u6539\u7684\u5c3a\u5bf8\u7279\u6027\uff1b\u6b64\u5916\uff0c\u8fd8\u63d0\u4f9b\u5341\u4e09\u79cd\u9884\u5b9a\u4e49\u7684\u6750\u6599\u3002\u8be5 App \u8ba1\u7b97\u590d\u5408\u6750\u6599\u7684\u7b49\u6548\u5bc6\u5ea6\u3001\u70ed\u5bb9\u548c\u5bfc\u70ed\u7cfb\u6570\u6216\u6269\u6563\u7cfb\u6570\u3002");

    model.mesh().clearMeshes();

    model.sol("htcp1sol").clearSolutionData();

    model.label("equivalent_properties_of_periodic_microstructures.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    run4(model);
  }

}
