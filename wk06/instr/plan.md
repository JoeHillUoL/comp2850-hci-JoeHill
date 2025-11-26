# Instrumentation Plan — Week 6

**Purpose**: Capture objective metrics for Week 9 task-based pilots and Week 10 analysis.

---

## Events to Log

### 1. Task Created
**Trigger**: POST /tasks (success)
**Fields**:
- `ts_iso`: ISO 8601 timestamp (e.g., 2025-01-15T14:23:45Z)
- `session_id`: Anonymous 6-char hex (e.g., `P1_a3f7`)
- `request_id`: Unique per request (for tracing)
- `task_code`: `T3_add` (pilot task identifier)
- `step`: `submit`
- `outcome`: `success` | `validation_error`
- `ms`: Time from request start to response (server-side)
- `http_status`: 200 (success) | 400 (validation error)
- `js_mode`: `js-on` | `js-off`

**Why**: Measure task completion time, compare HTMX vs. no-JS.

---

### 2. Validation Error
**Trigger**: POST /tasks (blank title)
**Fields**: Same as Task Created, but `outcome=validation_error`, `http_status=400`

**Why**: Count errors as usability metric; high error rate = poor UX.

---

### 3. Task Deleted
**Trigger**: POST /tasks/{id}/delete (success)
**Fields**: Same structure as Task Created, but `task_code=T4_delete`

**Why**: Measure delete task time; verify live region announcement.

---

## Data Storage

- **Format**: CSV (human-readable, easy to analyse in Excel/Google Sheets)
- **Location**: `data/metrics.csv` (local file, not cloud)
- **Schema**:
  ```csv
  ts_iso,session_id,request_id,task_code,step,outcome,ms,http_status,js_mode




# Week 9 Evaluation Planning Sketch

## Events to Log

Based on backlog priorities:
- Task creation (time, validation errors)
- Task deletion (confirmation shown?)
- Filter usage (reset frequency)
- Keyboard navigation (Tab presses per task)

## Metrics to Capture

- **Time-on-task**: How long to add/edit/delete?
- **Error rate**: How often do validation errors occur?
- **Completion rate**: Can people complete tasks without help?
- **Confidence ratings**: Post-task subjective feedback

## Test Scenarios

1. Add 3 tasks (test confirmation feedback)
2. Delete task with keyboard only (test accessibility)
3. Filter then page-reload (test filter persistence)

Details in Week 9 Lab 1.
